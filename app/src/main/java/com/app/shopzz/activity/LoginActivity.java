package com.app.shopzz.activity;

import android.app.Activity;
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

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.OnValidationClick;
import com.app.shopzz.utility.Utils;
import com.app.shopzz.utility.ValidationClass;


/**
 * Created by AGS on 08-05-2018.
 */

public class LoginActivity extends AppCompatActivity implements OnValidationClick {


    private RelativeLayout rlParent;

    private EditText etEmailAddress;
    private EditText etPassword;
    private TextView tvForgotPassword;
    private TextView tvLogin;
    private TextView tvCreateAccount;
    private RelativeLayout rlGoogle;
    private RelativeLayout rlFacebook;
    private ImageView ivShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    public void onViewClick(View v) {
        Intent mIntent;
        switch (v.getId()) {
            case R.id.tvLogin:
                if (isValidate()) {
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
        }
    }

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
                    return true;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void OnValidationClick(View mView) {
        mView.requestFocus();
        Utils.getInstance().launchKeyboard(this, (EditText) mView);
    }
}