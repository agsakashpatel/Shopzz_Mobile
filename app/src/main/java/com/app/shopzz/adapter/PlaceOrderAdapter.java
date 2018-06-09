package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.PlaceOrderModel;
import com.app.shopzz.viewholder.PlaceOrderViewHolder;

import java.util.List;

public class PlaceOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlaceOrderModel> placeOrderModels;
    private Context context;
    private OnClickListener onClickListener;

    public PlaceOrderAdapter(Context context, List<PlaceOrderModel> placeOrderModels,
                             OnClickListener onClickListener) {
        this.placeOrderModels = placeOrderModels;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_ac_place_order,
                        parent, false);
        return new PlaceOrderViewHolder(layoutView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PlaceOrderModel placeOrderModel = placeOrderModels.get(position);

        final PlaceOrderViewHolder placeOrderViewHolder =
                (PlaceOrderViewHolder) holder;

        if (placeOrderModel.isSelected())
            placeOrderViewHolder.ivSelectAddress.setImageResource(R.mipmap.radio_selected);
        else
            placeOrderViewHolder.ivSelectAddress.setImageResource(R.mipmap.radio_normal);

        placeOrderViewHolder.rlParent.setTag(position);
        placeOrderViewHolder.rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unSelectAll();
                int position = (int) view.getTag();
                placeOrderModels.get(position).setSelected(true);
                onClickListener.onClick(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return placeOrderModels.size();
    }

    private void unSelectAll() {
        try {
            for (int i = 0; i < placeOrderModels.size(); i++) {
                placeOrderModels.get(i).setSelected(false);
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("unselectall", e.getMessage());
        }
    }
}
