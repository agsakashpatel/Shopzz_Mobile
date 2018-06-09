package com.app.shopzz.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;

public class CartViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout llParent;
    public CheckBox cbRemove;
    public RelativeLayout rlProductInfo;
    public TextView tvQuantity;
    public TextView tvMultiply;
    public TextView tvProductColor;
    public RelativeLayout rlSelectProductColorAndQuantity;
    public TextView tvSelectProductColor;
    public TextView tvSelectProductQuantity;

    public CartViewHolder(View itemView) {
        super(itemView);

        llParent = GenericView.findViewById(itemView, R.id.llParent);
        cbRemove = GenericView.findViewById(itemView, R.id.cbRemove);
        rlProductInfo = GenericView.findViewById(itemView, R.id.rlProductInfo);
        tvQuantity = GenericView.findViewById(itemView, R.id.tvQuantity);
        tvMultiply = GenericView.findViewById(itemView, R.id.tvMultiply);
        tvProductColor = GenericView.findViewById(itemView, R.id.tvProductColor);
        rlSelectProductColorAndQuantity = GenericView.findViewById(itemView, R.id.rlSelectProductColorAndQuantity);
        tvSelectProductColor = GenericView.findViewById(itemView, R.id.tvSelectProductColor);
        tvSelectProductQuantity = GenericView.findViewById(itemView, R.id.tvSelectProductQuantity);
    }

}
