package com.app.shopzz.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.shopzz.BaseApplication;

public class ConnectivityHelper {
    /**
     * Checking for all possible internet providers
     **/
    public static boolean isConnectingToInternet() {
        final ConnectivityManager connectivity = (ConnectivityManager) BaseApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    return true;
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}