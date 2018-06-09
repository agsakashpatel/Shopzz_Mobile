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

public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout llParent;
    public ImageView ivRatingOne;
    public ImageView ivRatingTwo;
    public ImageView ivRatingThree;
    public ImageView ivRatingFour;
    public ImageView ivRatingFive;

    public OrderDetailsViewHolder(View itemView) {
        super(itemView);

        llParent = GenericView.findViewById(itemView, R.id.llParent);
        ivRatingOne = GenericView.findViewById(itemView, R.id.ivRatingOne);
        ivRatingTwo = GenericView.findViewById(itemView, R.id.ivRatingTwo);
        ivRatingThree = GenericView.findViewById(itemView, R.id.ivRatingThree);
        ivRatingFour = GenericView.findViewById(itemView, R.id.ivRatingFour);
        ivRatingFive = GenericView.findViewById(itemView, R.id.ivRatingFive);
    }

}
