package com.app.shopzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.adapter.OnSaleSeeMoreAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.models.OnSaleModel;

import java.util.ArrayList;
import java.util.List;

public class OnSaleActivity extends AppCompatActivity {

    private ImageView ivBack;
    private RecyclerView recyclerViewGrid;

    List<OnSaleModel> onSaleModels;
    OnSaleSeeMoreAdapter onSaleSeeMoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_on_sale);

        recyclerViewGrid = GenericView.findViewById(this, R.id.recyclerViewGrid);
        ivBack = GenericView.findViewById(this, R.id.ivBack);
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
        switch (v.getId()) {
            case R.id.ivBack:
                onBackPressed();
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
