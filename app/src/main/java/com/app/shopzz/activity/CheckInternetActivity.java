package com.app.shopzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;

public class CheckInternetActivity extends AppCompatActivity {

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private TextView tvRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_check_internet);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.close_icon);
        tvTitle.setText(getResources().getString(R.string.str_your_cart));
        tvTitle.setTextColor(getResources().getColor(R.color.color_8));
        tvEdit.setText(getResources().getString(R.string.str_edit));

        tvRetry = GenericView.findViewById(this, R.id.tvRetry);
    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.tvEdit:
                break;
            case R.id.tvRetry:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
