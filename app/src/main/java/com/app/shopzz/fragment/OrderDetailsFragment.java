package com.app.shopzz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.activity.ShopzzActivity;
import com.app.shopzz.adapter.OrderDetailsAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.IViewClick;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.OrderDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsFragment extends Fragment implements IViewClick,OnClickListener {

    private ShopzzActivity parent;

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;
    private RecyclerView recyclerView;

    List<OrderDetailsModel> orderDetailsModels;
    OrderDetailsAdapter orderDetailsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (ShopzzActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_thank_you, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ivClose = GenericView.findViewById(getView(), R.id.ivClose);
        tvTitle = GenericView.findViewById(getView(), R.id.tvTitle);
        tvEdit = GenericView.findViewById(getView(), R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.back_blue);
        tvTitle.setText(getResources().getString(R.string.str_order_detail));
        tvEdit.setVisibility(View.INVISIBLE);

        recyclerView = GenericView.findViewById(getView(), R.id.recyclerView);
        setListData();
        setList();
    }


    private void setListData() {
        orderDetailsModels = new ArrayList<>();
        orderDetailsAdapter = new OrderDetailsAdapter(parent, orderDetailsModels, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(parent);
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

}
