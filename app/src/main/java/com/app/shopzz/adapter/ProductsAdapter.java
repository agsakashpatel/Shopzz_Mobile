package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.model.Products;

import java.util.List;

/**
 * Created by AGS on 08-06-2018.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private List<Products> productList;

    public ProductsAdapter(Context mContext, List<Products> mProductList) {
        this.context = mContext;
        this.productList = mProductList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_product, null);
        ViewHolder mh = new ViewHolder(v);
        return mh;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Products mProduct = productList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewPager viewPager;
        private LinearLayout llFavourite;
        private ImageView iv_imgFavourite;
        private TextView tv_txtProductName;
        private TextView tv_txtStoreName;
        private TextView tv_txtProductPrice;
        private LinearLayout llAdvertisement;
        private ImageView iv_imgAdvertisement;
        private TextView tv_txtAdvertisementName;
        private TextView tv_txtCountryName;
        private TextView tv_txtCallMe;

        public ViewHolder(View view) {
            super(view);

            viewPager = GenericView.findViewById(view, R.id.viewPager);
            llFavourite = GenericView.findViewById(view, R.id.llFavourite);
            iv_imgFavourite = GenericView.findViewById(view, R.id.iv_imgFavourite);
            tv_txtProductName = GenericView.findViewById(view, R.id.tv_txtProductName);
            tv_txtStoreName = GenericView.findViewById(view, R.id.tv_txtStoreName);
            tv_txtProductPrice = GenericView.findViewById(view, R.id.tv_txtProductPrice);
            llAdvertisement = GenericView.findViewById(view, R.id.llAdvertisement);
            iv_imgAdvertisement = GenericView.findViewById(view, R.id.iv_imgAdvertisement);
            tv_txtAdvertisementName = GenericView.findViewById(view, R.id.tv_txtAdvertisementName);
            tv_txtCountryName = GenericView.findViewById(view, R.id.tv_txtCountryName);
            tv_txtCallMe = GenericView.findViewById(view, R.id.tv_txtCallMe);
        }
    }
}