package com.app.shopzz.api;


import com.app.shopzz.model.Area;
import com.app.shopzz.model.CheckVersion;
import com.app.shopzz.model.Category;
import com.app.shopzz.model.Country;
import com.app.shopzz.model.Products;

import java.lang.reflect.Type;

public enum RequestCode {

    CHECK_VERSION(CheckVersion.class),
    CATEGORY(Category[].class),
    PRODUCTS(Products[].class),
    USER_REGISTRATION(String.class),
    USER_LOGIN(String.class),
    COUNTRY(Country[].class),
    AREA(Area[].class),
    FORGOT_PASSWORD(String.class),
    SOCIAL_LOGIN(String.class);


    private Class<?> localClass = null;
    private Type localType = null;

    RequestCode(Class<?> localClass) {
        this.localClass = localClass;
    }

    RequestCode(Type localType) {
        this.localType = localType;
        this.localClass = localType.getClass();
    }

    public Class<?> getLocalClass() {
        return localClass;
    }

    public Type getLocalType() {
        return localType;
    }
}