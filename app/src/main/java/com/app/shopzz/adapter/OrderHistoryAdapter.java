package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.OrderHistoryModel;
import com.app.shopzz.viewholder.OrderDetailsViewHolder;
import com.app.shopzz.viewholder.OrderHistoryViewHolder;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderHistoryModel> orderHistoryModels;
    private Context context;
    private OnClickListener onClickListener;

    public OrderHistoryAdapter(Context context, List<OrderHistoryModel> OrderHistoryModel,
                               OnClickListener onClickListener) {
        this.orderHistoryModels = OrderHistoryModel;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_ac_order_history,
                        parent, false);
        return new OrderHistoryViewHolder(layoutView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderHistoryModel orderHistoryModel = orderHistoryModels.get(position);

        final OrderHistoryViewHolder orderHistoryViewHolder =
                (OrderHistoryViewHolder) holder;


        orderHistoryViewHolder.llParent.setTag(position);
        orderHistoryViewHolder.llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                onClickListener.onClick(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderHistoryModels.size();
    }

}
