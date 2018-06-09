package com.app.shopzz;

import android.app.Application;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

/**
 * Created by AGS on 07-05-2018.
 */

public class BaseApplication extends Application {
    private static final int REQUEST_TIMEOUT = 50000;
    private static final int MAX_RETRIES = 2;
    private static final int BACK_OF_MULTIPLIER = 2;

    public final String TAG = BaseApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;

    public static BaseApplication appInstance;

    public static HashMap<String, Typeface> mTypefaceMap;

    public static synchronized BaseApplication getInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;
        mTypefaceMap = new HashMap<>();

        getTypeface(BaseConstant.LATO_REGULAR);
        getTypeface(BaseConstant.LATO_BOLD);
        getTypeface(BaseConstant.LATO_BLACK);
        getTypeface(BaseConstant.LATO_ITALIC);
        getTypeface(BaseConstant.LATO_LIGHT_ITALIC);
    }

    /**
     * getTypeface from the path specified in argument.
     *
     * @param file : font file path from assets.
     * @return font file object
     */
    public Typeface getTypeface(final String file) {
        Typeface result = mTypefaceMap.get(file);
        if (result == null) {
            result = Typeface.createFromAsset(getAssets(), file);
            mTypefaceMap.put(file, result);
        }
        return result;
    }

    /*-------------------------------Volley Related methods--------------------------------*/

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * This method add the request in the requestQueue with specific tag
     *
     * @param tag (String)  : to identify the request type e.g. <code>requestCode.name() OR RequestCode.CUSTOMER_LOGIN</code>
     * @param req (Request) : volley request type e.g. JSONObject,JSONArray,JSONString
     */
    public <T> void addToRequestQueue(String tag, Request<T> req) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    /**
     * This method returns the volley requestQueue
     *
     * @return mRequestQueue (RequestQueue)  : to return RequestQueue instance
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * This method set timeout duration of Request
     *
     * @param req (Request) : volley request type e.g. JSONObject, JSONArray, JSONString
     */
    public <T> void setRequestTimeout(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(
                REQUEST_TIMEOUT,
                MAX_RETRIES,
                BACK_OF_MULTIPLIER));
    }

    public void cancelPendingRequests(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void cancelAllRequest() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(this);
        }
    }
}
