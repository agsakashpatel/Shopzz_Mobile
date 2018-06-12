package com.app.shopzz.listener;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.json.JSONObject;

/**
 * Created by Mandy on 21-09-2017.
 */

public interface IGoogleResultInfo
{
    void getGoogleResult(GoogleSignInResult result);
}
