package com.app.shopzz.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;

public class PlaceOrderViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout rlParent;
    public ImageView ivSelectAddress;

    public PlaceOrderViewHolder(View itemView) {
        super(itemView);

        rlParent = GenericView.findViewById(itemView, R.id.rlParent);
        ivSelectAddress = GenericView.findViewById(itemView, R.id.ivSelectAddress);

    }

}
