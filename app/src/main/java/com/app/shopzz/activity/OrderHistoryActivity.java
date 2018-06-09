package com.app.shopzz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.adapter.OrderHistoryAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.OrderHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements OnClickListener {

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private RecyclerView recyclerView;

    List<OrderHistoryModel> orderHistoryModels;
    OrderHistoryAdapter orderHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_order_history);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.back_blue);
        tvTitle.setText(getResources().getString(R.string.str_order_history));
        tvEdit.setVisibility(View.INVISIBLE);

        recyclerView = GenericView.findViewById(this, R.id.recyclerView);
        setListData();
        setList();
    }

    private void setListData() {
        orderHistoryModels = new ArrayList<>();
        orderHistoryAdapter = new OrderHistoryAdapter(this, orderHistoryModels, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderHistoryAdapter);
    }

    private void setList() {
        orderHistoryModels.add(new OrderHistoryModel("hdfh"));
        orderHistoryModels.add(new OrderHistoryModel("hdfh"));
        orderHistoryModels.add(new OrderHistoryModel("hdfh"));
        orderHistoryModels.add(new OrderHistoryModel("hdfh"));
        orderHistoryModels.add(new OrderHistoryModel("hdfh"));
        orderHistoryModels.add(new OrderHistoryModel("hdfh"));
        orderHistoryModels.add(new OrderHistoryModel("hdfh"));
        orderHistoryAdapter.notifyDataSetChanged();
    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onClick(int position) {
        Intent mIntent = new Intent(this, OrderDetailsActivity.class);
        startActivity(mIntent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
