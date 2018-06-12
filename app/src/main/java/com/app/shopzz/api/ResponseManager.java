package com.app.shopzz.api;


import com.app.shopzz.helper.LoginHelper;
import com.app.shopzz.model.User;
import com.app.shopzz.utility.Debug;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created on 18-7-2017.
 */

public class ResponseManager {
    public static <T> Object parse(RequestCode requestCode, String response, Gson gson) throws JSONException {

        Debug.trace("Response:" + response);
        final JSONObject jsonObject = new JSONObject(response).getJSONObject("result");
        Object object;

        switch (requestCode) {
            case CHECK_VERSION:
                object = gson.fromJson(jsonObject.toString(), requestCode.getLocalClass());
                break;
            case CATEGORY:
                object = gson.fromJson(jsonObject.getJSONArray("categories").toString(), requestCode.getLocalClass());
                object = Arrays.asList((T[]) object);
                break;
            case PRODUCTS:
                object = gson.fromJson(jsonObject.getJSONArray("products").toString(), requestCode.getLocalClass());
                object = Arrays.asList((T[]) object);
                break;
            case AUTHENTICATION:
                object = parse_User_login(requestCode, jsonObject, gson);
                LoginHelper.getInstance().doLogin((User) object);
                break;
            case COUNTRY:
                object = gson.fromJson(jsonObject.getJSONArray("countries").toString(), requestCode.getLocalClass());
                object = Arrays.asList((T[]) object);
                break;
            case AREA:
                object = gson.fromJson(jsonObject.getJSONArray("areas").toString(), requestCode.getLocalClass());
                object = Arrays.asList((T[]) object);
                break;
            case FORGOT_PASSWORD:
                object = gson.fromJson(jsonObject.getJSONArray("forgot_password").toString(), requestCode.getLocalClass());
                break;
            default:
                object = response;
                break;
        }
        return object;
    }

    private static <T> Object parse_User_login(RequestCode requestCode, JSONObject responseObject, Gson gson) throws JsonSyntaxException, JSONException {
        return gson.fromJson(responseObject.toString(), requestCode.getLocalClass());
    }
}