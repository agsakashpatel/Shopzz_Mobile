package com.app.shopzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.core.Content;
import com.app.shopzz.customView.GenericView;

public class ContentActivity extends AppCompatActivity {
    public static String EXTRA_CONTENT = "content";

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;
    private Content mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_content);

        mContent = (Content) getIntent().getExtras().get(EXTRA_CONTENT);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle=GenericView.findViewById(this,R.id.tvTitle);
        tvEdit=GenericView.findViewById(this,R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.close_icon);
        tvEdit.setVisibility(View.INVISIBLE);

        switch (mContent) {
            case TERMS_CONDITION:
                tvTitle.setText(getResources().getString(R.string.str_terms_conditions));
                break;
            case PRIVACY_POLICY:
                tvTitle.setText(getResources().getString(R.string.title_privacy_policy));
                break;
        }
    }


    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_to_bottom);
    }

}
