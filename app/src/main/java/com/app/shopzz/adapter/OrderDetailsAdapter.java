package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.OrderDetailsModel;
import com.app.shopzz.viewholder.OrderDetailsViewHolder;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderDetailsModel> orderDetailsModels;
    private Context context;
    private OnClickListener onClickListener;

    public OrderDetailsAdapter(Context context, List<OrderDetailsModel> orderDetailsModels,
                               OnClickListener onClickListener) {
        this.orderDetailsModels = orderDetailsModels;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_ac_order_details,
                        parent, false);
        return new OrderDetailsViewHolder(layoutView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderDetailsModel orderDetailsModel = orderDetailsModels.get(position);

        final OrderDetailsViewHolder orderDetailsViewHolder =
                (OrderDetailsViewHolder) holder;


        orderDetailsViewHolder.llParent.setTag(position);
        orderDetailsViewHolder.llParent.setOnClickListener(new View.OnClickListener() {
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
        return orderDetailsModels.size();
    }

}
