package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.OnSaleModel;
import com.app.shopzz.viewholder.OnSaleViewHolder;
import com.app.shopzz.viewholder.PaginationProgressbarViewHolder;

import java.util.List;

public class OnSaleSeeMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    private List<OnSaleModel> onSaleModels;
    private Context context;
   // private OnClickListener onClickListener;

    public OnSaleSeeMoreAdapter(Context context, List<OnSaleModel> onSaleModels/*,
                                OnClickListener onClickListener*/) {
        this.onSaleModels = onSaleModels;
        this.context = context;
        //this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                View layoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_ac_on_sale_seemore,
                                parent, false);
                viewHolder = new OnSaleViewHolder(layoutView);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.list_item_pagination_progress,
                        parent, false);
                viewHolder = new PaginationProgressbarViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OnSaleModel onSaleModel = onSaleModels.get(position);

        switch (getItemViewType(position)) {
            case ITEM:

                final OnSaleViewHolder onSaleViewHolder =
                        (OnSaleViewHolder) holder;

               /* onSaleViewHolder.llParent.setTag(position);
                onSaleViewHolder.llParent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = (int) view.getTag();
                        onClickListener.onClick(position);
                    }
                });*/
                break;
            case LOADING:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return onSaleModels == null ? 0 : onSaleModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == onSaleModels.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public OnSaleModel getItem(int position) {
        return onSaleModels.get(position);
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        notifyItemInserted(onSaleModels.size() - 1);
    }

    public void removeLoadingFooter() {
        if (isLoadingAdded) {
            int position = onSaleModels.size() - 1;
            OnSaleModel onSaleModel = getItem(position);

            if (onSaleModel != null) {
                onSaleModels.remove(position);
                notifyItemRemoved(position);
            }
        }

        isLoadingAdded = false;
    }

    public void add(OnSaleModel onSaleModel) {
        onSaleModels.add(onSaleModel);
        notifyItemInserted(onSaleModels.size() - 1);
    }
}
