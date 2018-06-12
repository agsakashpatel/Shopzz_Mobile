package com.app.shopzz.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.app.shopzz.BaseApplication;
import com.app.shopzz.BaseConstant;
import com.app.shopzz.api.ServerConfig;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by AGS on 08-05-2018.
 */

public class Utils {
    private static Utils instance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    /**
     * Function is used to identify touch event. If user touches outside of keyboard on screen instead of input control then call hideAnimateDialog Keyboard method to hideAnimateDialog keyboard.
     *
     * @param view (View) contains parent view id of screens.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void setupOutSideTouchHideKeyboard(final View view) {

        // Set up touch listener for non-text box views to hideAnimateDialog keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(v);
                    return false;
                }

            });
        }

        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupOutSideTouchHideKeyboard(innerView);
            }
        }
    }

    public void hideKeyboard(View v) {
        InputMethodManager mgr = (InputMethodManager) BaseApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * Launch Keyboard on edittext Selection...
     *
     * @param mContext  :
     * @param mEditText
     */
    public void launchKeyboard(final Context mContext, final EditText mEditText) {
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(mEditText, 0);
            }
        }, 100);
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null || target.length() < 1) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    /**
     * Conversts Key String to Md5
     */
    public String getMD5EncryptedString() {
        String encTarget = ServerConfig.AUTHENTICATE_VALUE;
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception while encrypting to md5");
            e.printStackTrace();
        }
        mdEnc.update(encTarget.getBytes(), 0, encTarget.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }

    /**
     * This method give the App versionCode
     *
     * @return (int) version :  it return app version code e.g. 1, 2, 3
     * return version - e.g. 1, 2, 3
     */
    public int getAppVersionCode() {
        int version = 0;
        try {
            PackageInfo pInfo = BaseApplication.getInstance().getPackageManager().getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            version = pInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * This method gives device information like device name, device brand name, android version
     * and all required information
     *
     * @return deviceDetails (String) : return device details
     * e.g. deviceDetails -"\n************ DEVICE INFORMATION ***********\n
     * Brand: Android\n
     * Device: generic_x86_64\n
     * Model: Android SDK built for x86_64\n
     * Id: LMY48X\nProduct: sdk_google_phone_x86_64\n
     * \n************ FIRMWARE ************\n
     * SDK: 22\nRelease: 5.1.1\n
     * Incremental: 3287191\n"
     */
    public String getDeviceDetails() {

        String deviceDetails = "\n************ DEVICE INFORMATION ***********\n" +
                "Brand: " +
                Build.BRAND +
                BaseConstant.LINE_SEPARATOR +
                "Device: " +
                Build.DEVICE +
                BaseConstant.LINE_SEPARATOR +
                "Model: " +
                Build.MODEL +
                BaseConstant.LINE_SEPARATOR +
                "Id: " +
                Build.ID +
                BaseConstant.LINE_SEPARATOR +
                "Product: " +
                Build.PRODUCT +
                BaseConstant.LINE_SEPARATOR +
                "\n************ FIRMWARE ************\n" +
                "SDK: " +
                Build.VERSION.SDK_INT +
                BaseConstant.LINE_SEPARATOR +
                "Release: " +
                Build.VERSION.RELEASE +
                BaseConstant.LINE_SEPARATOR +
                "Incremental: " +
                Build.VERSION.INCREMENTAL +
                BaseConstant.LINE_SEPARATOR +
                "Version: "
                + getAppVersion() +
                BaseConstant.LINE_SEPARATOR +
                "VersionCode: " +
                getAppVersionCode() +
                BaseConstant.LINE_SEPARATOR;

        return deviceDetails;
    }

    /**
     * This method return the Application version
     *
     * @return (String) version : it return app version
     * return version - e.g. 1, 2, 3
     */
    public String getAppVersion() {

        String version = "";
        try {
            PackageInfo pInfo = BaseApplication.getInstance().getPackageManager().getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            version = pInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    public int[] getScreenWidthHeight() {
        WindowManager wm = (WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth(); // deprecated
        int height = display.getHeight(); // deprecated

        int[] wh = {width, height};

        return wh;
    }
}