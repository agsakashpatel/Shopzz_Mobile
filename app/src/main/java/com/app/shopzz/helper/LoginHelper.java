package com.app.shopzz.helper;

import com.app.shopzz.api.RestClient;
import com.app.shopzz.model.User;

public class LoginHelper {
    private static LoginHelper instance;
    private User user = null;

    private LoginHelper() {
        user = RestClient.gson.fromJson(PrefHelper.getString(PrefHelper.KEY_USER, ""),
                User.class);
    }

    public static LoginHelper getInstance() {
        if (null == instance) {
            instance = new LoginHelper();
        }
        return instance;
    }

    // USER METHODS
    public void doLogin(User user) {
        this.user = user;
        PrefHelper.setString(PrefHelper.KEY_USER, RestClient.gson.toJson(user));
    }

    public Boolean isLoggedIn() {
        return null != user;
    }

    public void logoutUser() {
        user = null;
        PrefHelper.setString(PrefHelper.KEY_USER, "");
    }

    public int getUserId() {
        return null == user ? 0 : user.id;
    }

    public String getFirstName() {
        return null == user ? "" : user.firstName;
    }

    public String getLastName() {
        return null == user ? "" : user.lastName;
    }

    public String getEmailAddress() {
        return null == user ? "" : user.email;
    }

    public String getMobile() {
        return null == user ? "" : user.mobileNo;
    }

    public String getGender() {
        return null == user ? "" : user.gender;
    }
}
