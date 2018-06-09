package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.HomeCategoryProductsModel;
import com.app.shopzz.viewholder.HomeCategoriesProductsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeCategoryProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<HomeCategoryProductsModel> homeCategoryModels;
    private Context context;
    private OnClickListener onClickListener;

    public HomeCategoryProductsAdapter(Context context, List<HomeCategoryProductsModel> homeCategoryModels,
                                       OnClickListener onClickListener) {
        this.homeCategoryModels = homeCategoryModels;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_frag_home_categories_products,
                        parent, false);
        return new HomeCategoriesProductsViewHolder(layoutView);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeCategoryProductsModel homeCategoryModel = homeCategoryModels.get(position);


        HomeCategoriesProductsViewHolder homeCategoriesViewHolder =
                (HomeCategoriesProductsViewHolder) holder;

       /* if (homeCategoryModel.getImages() != null) {
            homeCategoriesViewHolder.viewPager.setAdapter(new
                    HomeViewPagerAdapter(context, homeCategoryModel.getImages()));
            homeCategoriesViewHolder.circlePageIndicator.setViewPager(
                    homeCategoriesViewHolder.viewPager);
        } else {*/

       if (position==1){
           homeCategoriesViewHolder.llViewPager.setVisibility(View.GONE);
           homeCategoriesViewHolder.llAdvertisement.setVisibility(View.VISIBLE);
       }else {
           homeCategoriesViewHolder.llViewPager.setVisibility(View.VISIBLE);
           homeCategoriesViewHolder.llAdvertisement.setVisibility(View.GONE);
       }

        List<String> images = new ArrayList<>();
        for (int i = 0; i < homeCategoryModels.size(); i++) {
            images.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        }

        homeCategoriesViewHolder.viewPager.setAdapter(new
                HomeViewPagerAdapter(context, images));
        homeCategoriesViewHolder.circlePageIndicator.setViewPager(
                homeCategoriesViewHolder.viewPager);
        //}

        homeCategoriesViewHolder.llParent.setTag(position);
        homeCategoriesViewHolder.llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                onClickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeCategoryModels.size();
    }


}
