package com.app.shopzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.adapter.CartAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.OnCartClickListener;
import com.app.shopzz.models.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;
    private RecyclerView recyclerView;
    private TextView tvCheckOut;

    List<CartModel> cartModels;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cart);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.close_icon);
        tvTitle.setText(getResources().getString(R.string.str_your_cart));
        tvTitle.setTextColor(getResources().getColor(R.color.colorBlack));
        tvEdit.setText(getResources().getString(R.string.str_edit));

        recyclerView = GenericView.findViewById(this, R.id.recyclerView);
        tvCheckOut = GenericView.findViewById(this, R.id.tvCheckOut);
        tvCheckOut.setVisibility(View.VISIBLE);
        setListData();
        setList();
    }


    private void setListData() {
        cartModels = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartModels, onCartClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);
    }

    private void setList() {
        cartModels.add(new CartModel("hdfh", false));
        cartModels.add(new CartModel("hdfh", false));
        cartModels.add(new CartModel("hdfh", false));
        cartModels.add(new CartModel("hdfh", false));
        cartModels.add(new CartModel("hdfh", false));
        cartAdapter.notifyDataSetChanged();
    }


    OnCartClickListener onCartClickListener = new OnCartClickListener() {
        @Override
        public void onClick(int position) {

        }

        @Override
        public void onCheckboxClick(int position) {
            int count = cartAdapter.getSelectionCount();
            Log.e("count", "counts " + position);
            if (count == 0) {
                tvCheckOut.setVisibility(View.INVISIBLE);
            } else {
                tvCheckOut.setVisibility(View.VISIBLE);
            }
        }
    };

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
            case R.id.tvEdit:
                if (tvEdit.getText().toString().equalsIgnoreCase(getResources().getString(R.string.str_edit))) {
                    setEditMode(true);
                    tvEdit.setText(getResources().getString(R.string.str_done));
                    ivClose.setImageResource(R.mipmap.back_blue);
                    tvCheckOut.setVisibility(View.INVISIBLE);
                    tvTitle.setText(getResources().getString(R.string.str_edit_cart));
                } else if (tvEdit.getText().toString().equalsIgnoreCase(getResources().getString(R.string.str_done))) {
                    setEditMode(false);
                    tvCheckOut.setVisibility(View.VISIBLE);
                    ivClose.setImageResource(R.mipmap.close_icon);
                    tvEdit.setText(getResources().getString(R.string.str_edit));
                    tvTitle.setText(getResources().getString(R.string.str_your_cart));
                }
                break;
            case R.id.tvCheckOut:
                setEditMode(false);
                ivClose.setImageResource(R.mipmap.close_icon);
                tvEdit.setText(getResources().getString(R.string.str_edit));
                tvTitle.setText(getResources().getString(R.string.str_your_cart));
                break;
        }
    }

    private boolean setEditMode(boolean status) {
        if (cartAdapter != null) {
            cartAdapter.setEditMode(status);
            cartAdapter.notifyDataSetChanged();
        }
        if (status) {
            ivClose.setImageResource(R.mipmap.back_blue);
            tvCheckOut.setText(getResources().getString(R.string.str_remove));
            tvCheckOut.setBackground(getResources().getDrawable(R.drawable.ic_background_1));
        } else {
            ivClose.setImageResource(R.mipmap.close_icon);
            tvCheckOut.setText(getResources().getString(R.string.str_check_out));
            tvCheckOut.setBackground(getResources().getDrawable(R.drawable.ic_background_7));
        }
        return status;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
