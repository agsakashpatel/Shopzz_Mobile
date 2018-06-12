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
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PASSWORD_CONFIRMATION = "password_confirmation";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_MOBILE_NO = "mobile_no";
    public static final String KEY_IS_ACCEPT = "is_accept";
    public static final String KEY_COUNTRY_ID = "country_id";
    public static final String KEY_SOCIAL_TYPE = "social_type";
    public static final String KEY_SOCIAL_ID = "social_id";
    public static final String KEY_GOOGLE = "google";
    public static final String KEY_FACEBOOK = "facebook";
    public static final String KEY_FACEBOOK_ID = "id";


    public enum APIs {
        checkAppVersion("checkAppVersion"),
        getCategoryList("getCategoryList"),
        getProductList("getHomeDetails"),
        callUserRegister("register"),
        callUserLogin("login"),
        getCountryList("getCountryList"),
        getAreaList("getAreaList"),
        callForgotPassword("forgotPassword"),
        callSocialLogin("socialLogin");

        private final String URL;

        APIs(final String URL) {
            this.URL = URL;
        }

        public String getUrl() {
            return URL;
        }
    }
}