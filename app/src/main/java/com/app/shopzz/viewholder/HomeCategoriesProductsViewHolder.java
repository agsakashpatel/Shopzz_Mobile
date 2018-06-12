package com.app.shopzz.viewholder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.utility.CirclePageIndicator;

import me.relex.circleindicator.CircleIndicator;

public class HomeCategoriesProductsViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout llParent;
    public ViewPager viewPager;
    public CirclePageIndicator circlePageIndicator;
    public LinearLayout llFavourite;
    public ImageView ivLike;
    public LinearLayout llViewPager;
    public LinearLayout llAdvertisement;

    public HomeCategoriesProductsViewHolder(View itemView) {
        super(itemView);

        llParent = GenericView.findViewById(itemView, R.id.llParent);
        viewPager = GenericView.findViewById(itemView, R.id.viewPager);
        circlePageIndicator = GenericView.findViewById(itemView, R.id.circlePageIndicator);
        llFavourite = GenericView.findViewById(itemView, R.id.llFavourite);
        ivLike = GenericView.findViewById(itemView, R.id.iv_imgFavourite);
        llViewPager = GenericView.findViewById(itemView, R.id.llViewPager);
        llAdvertisement = GenericView.findViewById(itemView, R.id.llAdvertisement);

    }

}
