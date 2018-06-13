package com.app.shopzz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.shopzz.R;
import com.app.shopzz.adapter.OnSaleSeeMoreAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.models.OnSaleModel;

import java.util.ArrayList;
import java.util.List;

public class MyAccountActivity extends AppCompatActivity {

    private ImageView ivSettingOptions;
    private RelativeLayout rlAccount;
    private RelativeLayout rlMyAddresses;
    private RelativeLayout rlMyOrders;
    private RelativeLayout rlMyShopzz;
    private RecyclerView recyclerViewGrid;

    List<OnSaleModel> onSaleModels;
    OnSaleSeeMoreAdapter onSaleSeeMoreAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_account);


        ivSettingOptions = GenericView.findViewById(this, R.id.ivSettingOptions);
        rlAccount = GenericView.findViewById(this, R.id.rlAccount);
        rlMyAddresses = GenericView.findViewById(this, R.id.rlMyAddresses);
        rlMyOrders = GenericView.findViewById(this, R.id.rlMyOrders);
        rlMyShopzz = GenericView.findViewById(this, R.id.rlMyShopzz);
        recyclerViewGrid = GenericView.findViewById(this, R.id.recyclerViewGrid);
        setListData();
        setList();
    }

    private void setListData() {
        onSaleModels = new ArrayList<>();
        onSaleSeeMoreAdapter = new OnSaleSeeMoreAdapter(this, onSaleModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGrid.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGrid.setAdapter(onSaleSeeMoreAdapter);
    }

    private void setList() {
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleModels.add(new OnSaleModel("hdfh"));
        onSaleSeeMoreAdapter.notifyDataSetChanged();
    }

    public void onViewClick(View v) {
        Intent mIntent;
        switch (v.getId()) {
            case R.id.ivSettingOptions:
                mIntent = new Intent(this, SettingsActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.rlAccount:
                mIntent = new Intent(this, EditProfileActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.rlMyAddresses:
                mIntent = new Intent(this, MyAddressesActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.rlMyOrders:

                break;
            case R.id.rlMyShopzz:
                mIntent = new Intent(this, MyShopzzActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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
