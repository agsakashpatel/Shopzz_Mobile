package com.app.shopzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.adapter.OrderDetailsAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.OrderDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity implements OnClickListener {

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;
    private RecyclerView recyclerView;

    List<OrderDetailsModel> orderDetailsModels;
    OrderDetailsAdapter orderDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_order_details);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.back_blue);
        tvTitle.setText(getResources().getString(R.string.str_order_detail));
        tvEdit.setVisibility(View.INVISIBLE);

        recyclerView = GenericView.findViewById(this, R.id.recyclerView);
        setListData();
        setList();
    }

    private void setListData() {
        orderDetailsModels = new ArrayList<>();
        orderDetailsAdapter = new OrderDetailsAdapter(this, orderDetailsModels, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderDetailsAdapter);
    }

    private void setList() {
        orderDetailsModels.add(new OrderDetailsModel("hdfh"));
        orderDetailsModels.add(new OrderDetailsModel("hdfh"));
        orderDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {

    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
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
