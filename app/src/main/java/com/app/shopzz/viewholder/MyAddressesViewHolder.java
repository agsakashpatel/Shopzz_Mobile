package com.app.shopzz.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;

public class MyAddressesViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout llSelectAddress;
    public LinearLayout llEditAddress;
    public ImageView ivSelectAddress;

    public MyAddressesViewHolder(View itemView) {
        super(itemView);

        llSelectAddress = GenericView.findViewById(itemView, R.id.llSelectAddress);
        llEditAddress = GenericView.findViewById(itemView, R.id.llEditAddress);
        ivSelectAddress = GenericView.findViewById(itemView, R.id.ivSelectAddress);

    }

}
