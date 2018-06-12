package com.app.shopzz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.app.shopzz.R;
import com.app.shopzz.api.ApiList;
import com.app.shopzz.api.RequestCode;
import com.app.shopzz.api.RequestListener;
import com.app.shopzz.api.RestClient;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.IFacebookResultInfo;
import com.app.shopzz.listener.IGoogleResultInfo;
import com.app.shopzz.listener.OnValidationClick;
import com.app.shopzz.socialmediaintegration.FacebookIntegration;
import com.app.shopzz.socialmediaintegration.GoogleIntegration;
import com.app.shopzz.utility.Utils;
import com.app.shopzz.utility.ValidationClass;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by AGS on 08-05-2018.
 */

public class LoginActivity extends AppCompatActivity implements OnValidationClick, RequestListener
        , IFacebookResultInfo, IGoogleResultInfo {

    private RelativeLayout rlParent;
    private EditText etEmailAddress;
    private EditText etPassword;
    private TextView tvForgotPassword;
    private TextView tvLogin;
    private TextView tvCreateAccount;
    private RelativeLayout rlGoogle;
    private RelativeLayout rlFacebook;
    private ImageView ivShowPassword;

    String socialtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleIntegration.getInstance(LoginActivity.this).initGoogleSdk();
        setContentView(R.layout.ac_login);

        rlParent = GenericView.findViewById(this, R.id.rlParent);
        Utils.getInstance().setupOutSideTouchHideKeyboard(rlParent);

        etEmailAddress = GenericView.findViewById(this, R.id.etEmailAddress);
        etPassword = GenericView.findViewById(this, R.id.etPassword);
        ivShowPassword = GenericView.findViewById(this, R.id.ivShowPassword);
        tvForgotPassword = GenericView.findViewById(this, R.id.tvForgotPassword);
        tvLogin = GenericView.findViewById(this, R.id.tvLogin);
        tvCreateAccount = GenericView.findViewById(this, R.id.tvCreateAccount);
        rlGoogle = GenericView.findViewById(this, R.id.rlGoogle);
        rlFacebook = GenericView.findViewById(this, R.id.rlFacebook);

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    ivShowPassword.setVisibility(View.VISIBLE);
                    ivShowPassword.setImageResource(R.mipmap.eye_open);
                } else {
                    ivShowPassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookIntegration.getInstance(LoginActivity.this).initFacebookSdk();

    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleIntegration.getInstance(LoginActivity.this).onStartGoogle(this);
    }

    public void onViewClick(View v) {
        Intent mIntent;
        switch (v.getId()) {
            case R.id.tvLogin:
                if (isValidate()) {
                    callUserLogin();
                }
                break;
            case R.id.tvForgotPassword:
                mIntent = new Intent(this, ForgotPasswordActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.stay);
                break;
            case R.id.tvCreateAccount:
                mIntent = new Intent(this, RegistrationActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.ivShowPassword:
                if (etPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ivShowPassword.setImageResource(R.mipmap.eye_open);
                } else {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ivShowPassword.setImageResource(R.mipmap.eye_closed);
                }
                etPassword.setSelection(etPassword.length());
                break;
            case R.id.rlGoogle:
                GoogleIntegration.getInstance(LoginActivity.this).logInToGoogle();
                break;
            case R.id.rlFacebook:
                FacebookIntegration.getInstance(LoginActivity.this).getFbHashKey();
                FacebookIntegration.getInstance(LoginActivity.this).loginToFaceBook(this);
                break;
        }
    }

    //check velidations
    private boolean isValidate() {
        if (ValidationClass.isEmpty(etEmailAddress.getText().toString())) {
            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_email_validation), etEmailAddress, this);
            return false;
        } else {
            if (!ValidationClass.matchPattern(etEmailAddress.getText().toString().trim(), Patterns.EMAIL_ADDRESS.pattern())) {
                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_valid_email_validation), etEmailAddress, this);
                return false;
            } else {
                if (ValidationClass.isEmpty(etPassword.getText().toString())) {
                    ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_password_validation), etPassword, this);
                    return false;
                } else {
                    if (!(etPassword.getText().toString().length() > 5)) {
                        ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_password_length_validation), etPassword, this);
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    @Override
    public void OnValidationClick(View mView) {
        mView.requestFocus();
        Utils.getInstance().launchKeyboard(this, (EditText) mView);
    }

    private void callUserLogin() {
        JSONObject param = new JSONObject();
        try {
            param.put(ApiList.KEY_EMAIL, etEmailAddress.getText().toString());
            param.put(ApiList.KEY_PASSWORD, etPassword.getText().toString());
            RestClient.getInstance().post(this, Request.Method.POST, ApiList.APIs.callUserLogin.getUrl()
                    , param, this, RequestCode.AUTHENTICATION, true, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //get api callback response
    @Override
    public void onComplete(RequestCode requestCode, Object object, String message) {
        Intent mIntent;
        switch (requestCode) {
            case AUTHENTICATION:
                mIntent = new Intent(LoginActivity.this, ShopzzActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
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


    //social media integration
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FacebookIntegration.DEFAULT_AUTH_FACEBOOK_REQUEST_CODE:
                FacebookIntegration.getInstance(LoginActivity.this).callbackManager.onActivityResult(requestCode, resultCode, data);
                break;
            case GoogleIntegration.DEFAULT_AUTH_GOOGLE_REQUEST_CODE:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleIntegration.getInstance(LoginActivity.this).saveGooglePlusLoginData(result, this);
                break;
        }
    }

    //call social media api
    private void callSocialLogin(String email, String firstName, String lastName,
                                 String socialType, String socialiD) {
        JSONObject param = new JSONObject();
        try {
            param.put(ApiList.KEY_EMAIL, email);
            param.put(ApiList.KEY_FIRST_NAME, firstName);
            param.put(ApiList.KEY_LAST_NAME, lastName);
            param.put(ApiList.KEY_GENDER, getResources().getString(R.string.str_male));
            param.put(ApiList.KEY_MOBILE_NO, "");
            param.put(ApiList.KEY_SOCIAL_TYPE, socialType);
            param.put(ApiList.KEY_SOCIAL_ID, socialiD);
            RestClient.getInstance().post(this, Request.Method.POST, ApiList.APIs.callSocialLogin.getUrl()
                    , param, this, RequestCode.AUTHENTICATION, true, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //facebook integration interface method
    @Override
    public void getFacebookResult(JSONObject mJobjResult) {
        try {
            String firstName = mJobjResult.getString(ApiList.KEY_FIRST_NAME);
            String lastName = mJobjResult.getString(ApiList.KEY_LAST_NAME);
            String email = mJobjResult.getString(ApiList.KEY_EMAIL);
            String socialId = mJobjResult.getString(ApiList.KEY_FACEBOOK_ID);
            socialtype = ApiList.KEY_FACEBOOK;
            callSocialLogin(email, firstName, lastName, socialtype, socialId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //google integration interface method
    @Override
    public void getGoogleResult(GoogleSignInResult result) {
        String firstName = result.getSignInAccount().getGivenName();
        String lastName = result.getSignInAccount().getFamilyName();
        String displayName = result.getSignInAccount().getDisplayName();
        String email = result.getSignInAccount().getEmail();
        String socialId = result.getSignInAccount().getId();
        socialtype = ApiList.KEY_GOOGLE;
        callSocialLogin(email, firstName, lastName, socialtype, socialId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}


