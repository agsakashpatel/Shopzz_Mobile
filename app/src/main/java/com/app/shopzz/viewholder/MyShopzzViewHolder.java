package com.app.shopzz.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.utility.CircularImageView;

public class MyShopzzViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout llParent;
    public ImageView ivStoreLogo;
    public TextView tvStoreName;

    public MyShopzzViewHolder(View itemView) {
        super(itemView);

        llParent = GenericView.findViewById(itemView, R.id.llParent);
        ivStoreLogo = GenericView.findViewById(itemView, R.id.ivStoreLogo);
        tvStoreName = GenericView.findViewById(itemView, R.id.tvStoreName);

    }

}
