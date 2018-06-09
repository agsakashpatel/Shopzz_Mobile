package com.app.shopzz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.adapter.MyAddressesAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.OnSelectAddressListener;
import com.app.shopzz.models.MyAddressesModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddressesActivity extends AppCompatActivity {
    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private RecyclerView recyclerView;
    private RelativeLayout rlAddShippingAddress;

    List<MyAddressesModel> myAddressesModels;
    MyAddressesAdapter myAddressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_addresses);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.back_blue);
        tvTitle.setText(getResources().getString(R.string.str_my_addresses));
        tvEdit.setVisibility(View.INVISIBLE);

        recyclerView = GenericView.findViewById(this, R.id.recyclerView);
        rlAddShippingAddress = GenericView.findViewById(this, R.id.rlAddShippingAddress);
        setListData();
        setList();
    }

    private void setListData() {
        myAddressesModels = new ArrayList<>();
        myAddressesAdapter = new MyAddressesAdapter(this, myAddressesModels, onSelectAddressListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAddressesAdapter);
    }

    private void setList() {
        myAddressesModels.add(new MyAddressesModel("hdfh", true));
        myAddressesModels.add(new MyAddressesModel("hdfh", false));
        myAddressesModels.add(new MyAddressesModel("hdfh", false));
        myAddressesModels.add(new MyAddressesModel("hdfh", false));
        myAddressesModels.add(new MyAddressesModel("hdfh", false));
        myAddressesAdapter.notifyDataSetChanged();
    }


    OnSelectAddressListener onSelectAddressListener = new OnSelectAddressListener() {
        @Override
        public void onSelectAddressClick(int position) {

        }

        @Override
        public void onEditAddressClick(int position) {

        }
    };

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.rlAddShippingAddress:
                Intent mIntent = new Intent(MyAddressesActivity.this, AddAddressActivity.class);
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
