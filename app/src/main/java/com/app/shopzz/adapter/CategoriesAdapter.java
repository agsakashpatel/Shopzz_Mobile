package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.CategoriesModel;
import com.app.shopzz.models.OrderHistoryModel;
import com.app.shopzz.viewholder.CategoriesViewHolder;
import com.app.shopzz.viewholder.OrderHistoryViewHolder;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CategoriesModel> categoriesModels;
    private Context context;
    private OnClickListener onClickListener;

    public CategoriesAdapter(Context context, List<CategoriesModel> categoriesModels,
                             OnClickListener onClickListener) {
        this.categoriesModels = categoriesModels;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_frag_categories,
                        parent, false);
        return new CategoriesViewHolder(layoutView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CategoriesModel categoriesModel = categoriesModels.get(position);

        final CategoriesViewHolder categoriesViewHolder =
                (CategoriesViewHolder) holder;


        categoriesViewHolder.tvCategories.setTag(position);
        categoriesViewHolder.tvCategories.setOnClickListener(new View.OnClickListener() {
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
        return categoriesModels.size();
    }

}
