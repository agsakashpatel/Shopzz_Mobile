package com.app.shopzz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.core.Content;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.OnDialogButtonClick;

public class SettingsActivity extends AppCompatActivity {

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private RelativeLayout rlContactUs;
    private RelativeLayout rlRequestForVendor;
    private RelativeLayout rlSupport;
    private RelativeLayout rlTermsAndConditions;
    private RelativeLayout rlPrivacyPolicy;
    private RelativeLayout rlChangePassword;
    private RelativeLayout rlSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_settings);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.close_icon);
        tvTitle.setText(getResources().getString(R.string.str_setting));
        tvEdit.setVisibility(View.INVISIBLE);

        rlContactUs = GenericView.findViewById(this, R.id.rlContactUs);
        rlRequestForVendor = GenericView.findViewById(this, R.id.rlRequestForVendor);
        rlSupport = GenericView.findViewById(this, R.id.rlSupport);
        rlTermsAndConditions = GenericView.findViewById(this, R.id.rlTermsAndConditions);
        rlPrivacyPolicy = GenericView.findViewById(this, R.id.rlPrivacyPolicy);
        rlChangePassword = GenericView.findViewById(this, R.id.rlChangePassword);
        rlSignout = GenericView.findViewById(this, R.id.rlSignout);
    }

    public void onViewClick(View v) {
        Intent mIntent;
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.rlContactUs:
                break;
            case R.id.rlRequestForVendor:
                mIntent = new Intent(this, RequestForVendorActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.rlSupport:
                break;
            case R.id.rlTermsAndConditions:
                mIntent = new Intent(this, ContentActivity.class);
                mIntent.putExtra(ContentActivity.EXTRA_CONTENT, Content.TERMS_CONDITION);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_in_from_bottom);
                break;
            case R.id.rlPrivacyPolicy:
                mIntent = new Intent(this, ContentActivity.class);
                mIntent.putExtra(ContentActivity.EXTRA_CONTENT, Content.PRIVACY_POLICY);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_in_from_bottom);
                break;
            case R.id.rlChangePassword:
                mIntent = new Intent(this, ChangePasswordActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.rlSignout:
                ToastHelper.displayCustomDialog(this,
                        getResources().getString(R.string.str_dialog_title),
                        getResources().getString(R.string.str_dialog_message), rlSignout, onDialogButtonClick);
                break;
        }
    }


    OnDialogButtonClick onDialogButtonClick = new OnDialogButtonClick() {
        @Override
        public void OnCancleClick(View mView) {

        }

        @Override
        public void OnYesClick(View mView) {

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_to_bottom);
    }

}
