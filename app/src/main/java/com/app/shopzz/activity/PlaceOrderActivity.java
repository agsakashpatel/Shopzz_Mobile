package com.app.shopzz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.adapter.PlaceOrderAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.helper.ToastHelper;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.listener.OnValidationClick;
import com.app.shopzz.models.PlaceOrderModel;
import com.app.shopzz.utility.Utils;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity implements OnClickListener, OnValidationClick {

    private RecyclerView recyclerView;
    private TextView tvCheckOut;
    private RelativeLayout rlPromoCode;
    private RelativeLayout rlAddShippingAddress;
    private EditText etPromoCode;
    private LinearLayout llEditPromoCode;
    private TextView tvApplyPromoCode;
    private TextView tvPromoCode;
    private ImageView ivRemovePromoCode;
    private RadioButton rbCash;
    private RadioButton rbKNET;
    private TextView tvDiscount;
    private TextView tvDiscountPrice;

    List<PlaceOrderModel> placeOrderModels;
    PlaceOrderAdapter placeOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_place_order);

        recyclerView = GenericView.findViewById(this, R.id.recyclerView);
        tvCheckOut = GenericView.findViewById(this, R.id.tvCheckOut);
        llEditPromoCode = GenericView.findViewById(this, R.id.llEditPromoCode);
        rlPromoCode = GenericView.findViewById(this, R.id.rlPromoCode);
        rlAddShippingAddress = GenericView.findViewById(this, R.id.rlAddShippingAddress);
        etPromoCode = GenericView.findViewById(this, R.id.etPromoCode);
        tvApplyPromoCode = GenericView.findViewById(this, R.id.tvApplyPromoCode);
        tvPromoCode = GenericView.findViewById(this, R.id.tvPromoCode);
        ivRemovePromoCode = GenericView.findViewById(this, R.id.ivRemovePromoCode);
        rbCash = GenericView.findViewById(this, R.id.rbCash);
        rbKNET = GenericView.findViewById(this, R.id.rbKNET);
        tvDiscount = GenericView.findViewById(this, R.id.tvDiscount);
        tvDiscountPrice = GenericView.findViewById(this, R.id.tvDiscountPrice);

        setListData();
        setList();
    }

    private void setListData() {
        placeOrderModels = new ArrayList<>();
        placeOrderAdapter = new PlaceOrderAdapter(this, placeOrderModels, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(placeOrderAdapter);
    }

    private void setList() {
        placeOrderModels.add(new PlaceOrderModel("hdfh", false));
        placeOrderModels.add(new PlaceOrderModel("hdfh", false));
        placeOrderModels.add(new PlaceOrderModel("hdfh", false));
        placeOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {

    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.tvCheckOut:
                break;
            case R.id.rlAddShippingAddress:
                Intent mIntent = new Intent(PlaceOrderActivity.this, AddAddressActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.tvApplyPromoCode:
                if (etPromoCode.getText().toString().matches("")) {
                    ToastHelper.displayCustomToast(this, getResources().getString(R.string.str_enter_promo_code_validation)
                            , etPromoCode, this);
                } else {
                    rlPromoCode.setVisibility(View.VISIBLE);
                    llEditPromoCode.setVisibility(View.GONE);
                    tvDiscount.setTextColor(getResources().getColor(R.color.colorGreen));
                    tvDiscountPrice.setTextColor(getResources().getColor(R.color.colorGreen));
                    tvPromoCode.setText(etPromoCode.getText().toString());
                }
                break;
            case R.id.ivRemovePromoCode:
                llEditPromoCode.setVisibility(View.VISIBLE);
                rlPromoCode.setVisibility(View.GONE);
                tvDiscount.setTextColor(getResources().getColor(R.color.color_1));
                tvDiscountPrice.setTextColor(getResources().getColor(R.color.color_1));
                tvPromoCode.setText(null);
                etPromoCode.setText(null);
                break;
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
