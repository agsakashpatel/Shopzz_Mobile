package com.app.shopzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.OnValidationClick;
import com.app.shopzz.utility.Utils;
import com.app.shopzz.utility.ValidationClass;

/**
 * Created by AGS on 10-05-2018.
 */

public class ForgotPasswordActivity extends AppCompatActivity implements OnValidationClick {

    private LinearLayout llParent;
    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private EditText etEmailAddress;
    private TextView tvSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_forgot_password);

        llParent = GenericView.findViewById(this, R.id.llParent);
        Utils.getInstance().setupOutSideTouchHideKeyboard(llParent);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.close_icon);
        tvTitle.setText(getResources().getString(R.string.str_forgot_password_1));
        tvEdit.setVisibility(View.INVISIBLE);

        etEmailAddress = GenericView.findViewById(this, R.id.etEmailAddress);
        tvSubmit = GenericView.findViewById(this, R.id.tvSubmit);

    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.tvSubmit:
                if (isValidate()) {
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_to_bottom);
    }

    private boolean isValidate() {
        if (TextUtils.isEmpty(etEmailAddress.getText().toString())) {
            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_email_validation), etEmailAddress, this);
            return false;
        } else {
            if (!ValidationClass.matchPattern(etEmailAddress.getText().toString().trim(), Patterns.EMAIL_ADDRESS.pattern())) {
                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_valid_email_validation), etEmailAddress, this);
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void OnValidationClick(View mView) {
        mView.requestFocus();
        Utils.getInstance().launchKeyboard(this, (EditText) mView);
    }
}
