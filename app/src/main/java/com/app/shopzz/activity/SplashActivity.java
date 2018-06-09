package com.app.shopzz.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.app.shopzz.BaseConstant;
import com.app.shopzz.R;
import com.app.shopzz.api.ApiList;
import com.app.shopzz.api.RequestCode;
import com.app.shopzz.api.RequestListener;
import com.app.shopzz.api.RestClient;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.model.CheckVersion;
import com.app.shopzz.utility.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AGS on 07-05-2018.
 */

public class SplashActivity extends AppCompatActivity implements RequestListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);

        checkAppVersion();
    }

    private void checkAppVersion() {
        try {
            JSONObject param = new JSONObject();
            param.put(ApiList.KEY_DEVICE_NAME, Utils.getInstance().getDeviceDetails());
            param.put(ApiList.KEY_APP_VERSION, Utils.getInstance().getAppVersionCode());
            param.put(ApiList.KEY_DEVICE_TOKEN, "");
            param.put(ApiList.KEY_DEVICE_MODEL, "");
            param.put(ApiList.KEY_USER_ID, "");
            param.put(ApiList.KEY_APP_TYPE, BaseConstant.APP_TYPE);

            RestClient.getInstance().post(this, Request.Method.POST, ApiList.APIs.checkAppVersion.getUrl(), param,
                    this, RequestCode.CHECK_VERSION, false, true);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void redirectUser() {
        Intent mIntent = new Intent(this, ShopzzActivity.class);
        startActivity(mIntent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void onComplete(RequestCode requestCode, Object object, String message) {
        switch (requestCode) {
            case CHECK_VERSION:
                CheckVersion checkVersion = (CheckVersion) object;

                /**
                 * checkVersion.getIsUpdateType()
                 *                  type - 0: App Under Construction
                 *                  type - 1: No Update...
                 *                  type - 2: Optional Update...
                 *                  type - 3: Compulsory Update...
                 *
                 */
                switch (checkVersion.getIsUpdateType()) {
                    case 0:
                        break;
                    case 1:
                        redirectUser();
                        break;
                    case 2:
                        showUpdateDialog(getResources().getString(R.string.str_update), checkVersion.getAppUpdateMsg(), checkVersion.getAppUrl(), true);
                        break;
                    case 3:
                        break;
                }
                break;
        }
    }

    @Override
    public void onException(int statusCode, String error, RequestCode requestCode) {
        ToastHelper.displayCustomToast(this, error);
    }

    @Override
    public void onRetryRequest(RequestCode requestCode) {
    }

    private void showUpdateDialog(String title, String updateMessage, final String url, boolean isOptionalUpdate) {

        final Dialog dialog = new Dialog(this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(this).inflate(R.layout.diag_alert, null);

        TextView mTxtTitle = GenericView.findViewById(view, R.id.tv_txtTitle);
        TextView mTxtMessage = GenericView.findViewById(view, R.id.tv_txtMessage);

        Button mBtnButton1 = GenericView.findViewById(view, R.id.bt_btnButton1);
        Button mBtnButton2 = GenericView.findViewById(view, R.id.bt_btnButton2);

        mBtnButton1.setText(getResources().getString(R.string.str_cancel));
        mBtnButton2.setText(getResources().getString(R.string.str_yes));

        mBtnButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        mBtnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        mTxtTitle.setText(title);
        mTxtMessage.setText(updateMessage);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.show();
    }
}