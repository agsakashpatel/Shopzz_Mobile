package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
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

        public ViewHolder(View view) {
            super(view);
        }
    }
}