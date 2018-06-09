package com.app.shopzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.IViewClick;
import com.app.shopzz.listener.OnValidationClick;
import com.app.shopzz.utility.Utils;
import com.app.shopzz.utility.ValidationClass;

public class ChangePasswordActivity extends AppCompatActivity implements OnValidationClick {

    private LinearLayout llParent;
    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private EditText etCurrentPassword;
    private EditText etNewPassword;
    private EditText etRetypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_change_password);

        llParent = GenericView.findViewById(this, R.id.llParent);
        Utils.getInstance().setupOutSideTouchHideKeyboard(llParent);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.back_blue);
        tvTitle.setText(getResources().getString(R.string.str_change_password));
        tvEdit.setText(getResources().getString(R.string.str_save));

        etCurrentPassword = GenericView.findViewById(this, R.id.etCurrentPassword);
        etNewPassword = GenericView.findViewById(this, R.id.etNewPassword);
        etRetypePassword = GenericView.findViewById(this, R.id.etRetypePassword);
    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.tvEdit:
                break;
        }
    }

    private boolean isValidate() {
        if (ValidationClass.isEmpty(etCurrentPassword.getText().toString())) {
            ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_current_password_validation), etCurrentPassword, this);
            return false;
        } else {
            if (ValidationClass.isEmpty(etNewPassword.getText().toString())) {
                ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_new_password_validation), etNewPassword, this);
                return false;
            } else {
                if (ValidationClass.isEmpty(etRetypePassword.getText().toString())) {
                    ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_retype_password_validation), etRetypePassword, this);
                    return false;
                } else {
                    if (!etNewPassword.getText().toString().equalsIgnoreCase(etRetypePassword.getText().toString())) {
                        ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_password_confirmation_validation), etRetypePassword, this);
                        return false;
                    } else {
                        return true;
                    }
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
