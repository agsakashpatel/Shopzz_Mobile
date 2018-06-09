package com.app.shopzz.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    public TextView tvCategories;

    public CategoriesViewHolder(View itemView) {
        super(itemView);

        tvCategories = GenericView.findViewById(itemView, R.id.tvCategories);

    }

}
