package com.app.shopzz.api;

/**
 * Created on 16-8-2017.
 */

public class ApiList {

    //header key
    public static final String KEY_CONTENT = "Content-Type";
    public static final String KEY_CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String KEY_AUTHENTICATE = "Authentication";

    public static final String KEY_APP_VERSION = "app_version";
    public static final String KEY_DEVICE_NAME = "device_name";
    public static final String KEY_DEVICE_TOKEN = "device_token";
    public static final String KEY_DEVICE_MODEL = "device_model";
    public static final String KEY_APP_TYPE = "app_type";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_PAGE = "page";


    public enum APIs {
        checkAppVersion("checkAppVersion"),
        getCategoryList("getCategoryList"),
        getProductList("getHomeDetails");

        private final String URL;

        APIs(final String URL) {
            this.URL = URL;
        }

        public String getUrl() {
            return URL;
        }
    }
}