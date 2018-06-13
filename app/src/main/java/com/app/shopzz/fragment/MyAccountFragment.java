package com.app.shopzz.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.shopzz.R;
import com.app.shopzz.activity.MyAddressesActivity;
import com.app.shopzz.activity.MyShopzzActivity;
import com.app.shopzz.activity.SettingsActivity;
import com.app.shopzz.activity.ShopzzActivity;
import com.app.shopzz.adapter.OnSaleSeeMoreAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.IViewClick;
import com.app.shopzz.models.OnSaleModel;

import java.util.ArrayList;
import java.util.List;

public class MyAccountFragment extends Fragment implements IViewClick {
    private ShopzzActivity parent;

    private ImageView ivSettingOptions;
    private RelativeLayout rlAccount;
    private RelativeLayout rlMyAddresses;
    private RelativeLayout rlMyOrders;
    private RelativeLayout rlMyShopzz;
    private RecyclerView recyclerViewGrid;

    List<OnSaleModel> onSaleModels;
    OnSaleSeeMoreAdapter onSaleSeeMoreAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (ShopzzActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_account, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ivSettingOptions = GenericView.findViewById(getView(), R.id.ivSettingOptions);
        rlAccount = GenericView.findViewById(getView(), R.id.rlAccount);
        rlMyAddresses = GenericView.findViewById(getView(), R.id.rlMyAddresses);
        rlMyOrders = GenericView.findViewById(getView(), R.id.rlMyOrders);
        rlMyShopzz = GenericView.findViewById(getView(), R.id.rlMyShopzz);
        recyclerViewGrid = GenericView.findViewById(getView(), R.id.recyclerViewGrid);
        setListData();
        setList();
    }

    private void setListData() {
        onSaleModels = new ArrayList<>();
        onSaleSeeMoreAdapter = new OnSaleSeeMoreAdapter(parent, onSaleModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(parent, 2);
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

    @Override
    public void onViewClick(View v) {
        Intent mIntent;
        switch (v.getId()) {
            case R.id.ivSettingOptions:
                mIntent = new Intent(parent, SettingsActivity.class);
                startActivity(mIntent);
                parent.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.stay);
                break;
            case R.id.rlAccount:
                break;
            case R.id.rlMyAddresses:
                mIntent = new Intent(parent, MyAddressesActivity.class);
                startActivity(mIntent);
                parent.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.rlMyOrders:
//                mIntent = new Intent(parent, OrderHistoryActivity.class);
//                startActivity(mIntent);
//                parent.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
//                break;
            case R.id.rlMyShopzz:
                mIntent = new Intent(parent, MyShopzzActivity.class);
                startActivity(mIntent);
                parent.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }
}