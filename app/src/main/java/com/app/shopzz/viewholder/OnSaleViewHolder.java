package com.app.shopzz.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;

public class OnSaleViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout llParent;

    public OnSaleViewHolder(View itemView) {
        super(itemView);

        llParent = GenericView.findViewById(itemView, R.id.llParent);
    }

}
