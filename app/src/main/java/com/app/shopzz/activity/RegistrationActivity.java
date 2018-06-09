package com.app.shopzz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.core.Content;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.OnValidationClick;
import com.app.shopzz.utility.Utils;
import com.app.shopzz.utility.ValidationClass;

/**
 * Created by AGS on 10-05-2018.
 */

public class RegistrationActivity extends AppCompatActivity implements OnValidationClick {

    private RelativeLayout rlParent;
    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etMobileNumber;
    private EditText etEmailAddress;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private ImageView ivTermsAndConditions;
    private TextView tvTermsAndConditions;
    private TextView tvRegister;
    private boolean isChecked = true;

    private int startTerms = 9, endTerms = 12;
    private int startPrivacy = 17, endPrivacy = 31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_register);

        rlParent = GenericView.findViewById(this, R.id.rlParent);
        Utils.getInstance().setupOutSideTouchHideKeyboard(rlParent);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        tvTitle.setText(getResources().getString(R.string.str_registration));
        tvEdit.setVisibility(View.INVISIBLE);

        etFirstName = GenericView.findViewById(this, R.id.etFirstName);
        etLastName = GenericView.findViewById(this, R.id.etLastName);
        etMobileNumber = GenericView.findViewById(this, R.id.etMobileNumber);
        etEmailAddress = GenericView.findViewById(this, R.id.etEmailAddress);
        rgGender = GenericView.findViewById(this, R.id.rgGender);
        rbMale = GenericView.findViewById(this, R.id.rbMale);
        rbFemale = GenericView.findViewById(this, R.id.rbFemale);
        etPassword = GenericView.findViewById(this, R.id.etPassword);
        etConfirmPassword = GenericView.findViewById(this, R.id.etConfirmPassword);
        ivTermsAndConditions = GenericView.findViewById(this, R.id.ivTermsAndConditions);
        tvTermsAndConditions = GenericView.findViewById(this, R.id.tvTermsAndConditions);
        tvRegister = GenericView.findViewById(this, R.id.tvRegister);

        Spannable span = Spannable.Factory.getInstance().newSpannable(getString(R.string.str_terms_privacy));
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(RegistrationActivity.this, ContentActivity.class);
                mIntent.putExtra(ContentActivity.EXTRA_CONTENT, Content.TERMS_CONDITION);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.stay);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        }, startTerms, endTerms, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(RegistrationActivity.this, ContentActivity.class);
                mIntent.putExtra(ContentActivity.EXTRA_CONTENT, Content.PRIVACY_POLICY);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.stay);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        }, startPrivacy, endPrivacy, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.tv_login_bg_color)), startTerms, endTerms, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.tv_login_bg_color)), startPrivacy, endPrivacy, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTermsAndConditions.setText(span);
        tvTermsAndConditions.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.ivTermsAndConditions:
                setEditMode();
                break;
            case R.id.tvRegister:
                if (isValidate()) {

                }
                break;
        }
    }

    private boolean isValidate() {
        if (ValidationClass.isEmpty(etFirstName.getText().toString())) {
            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_first_name_validation), etFirstName, this);
            return false;
        } else {
            if (ValidationClass.isEmpty(etLastName.getText().toString())) {
                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_last_name_validation), etLastName, this);
                return false;
            } else {
                if (ValidationClass.isEmpty(etMobileNumber.getText().toString())) {
                    ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_mobile_validation), etMobileNumber, this);
                    return false;
                } else {
                    if (etMobileNumber.getText().toString().length() != 10) {
                        ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_mobile_length_validation), etMobileNumber, this);
                        return false;
                    } else {
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
                                    if (ValidationClass.isEmpty(etConfirmPassword.getText().toString())) {
                                        ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_confirm_password_validation), etConfirmPassword, this);
                                        return false;
                                    } else {
                                        if (!etPassword.getText().toString().equalsIgnoreCase(etConfirmPassword.getText().toString())) {
                                            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_password_confirmation_validation), etConfirmPassword, this);
                                            return false;
                                        } else {
                                            if (isChecked) {
                                                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_privacy_policy_validation), ivTermsAndConditions, this);
                                                return false;
                                            } else {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void setEditMode() {
        if (isChecked) {
            ivTermsAndConditions.setImageResource(R.mipmap.radio_selected);
            isChecked = false;
        } else {
            ivTermsAndConditions.setImageResource(R.mipmap.radio_normal);
            isChecked = true;
        }
    }

    @Override
    public void OnValidationClick(View mView) {
        mView.requestFocus();
        Utils.getInstance().launchKeyboard(this, (EditText) mView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}