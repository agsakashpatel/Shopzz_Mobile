package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopzz.BaseApplication;
import com.app.shopzz.BaseConstant;
import com.app.shopzz.R;
import com.app.shopzz.api.ServerConfig;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AGS on 08-06-2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context mContext, List<Category> mCategoryList) {
        this.context = mContext;
        this.categoryList = mCategoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_category, null);
        ViewHolder mh = new ViewHolder(v);
        return mh;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category mCategory = categoryList.get(position);

        if (!mCategory.getCategoryImage().equalsIgnoreCase("")) {
            Picasso.with(context).load(ServerConfig.CATEGORY_IMAGE + mCategory.getCategoryImage()).into(holder.mImgCategory);
        }

        holder.mTxtName.setText(mCategory.getCategoryName());
        holder.mLinearParent.setTag(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLinearParent;
        ImageView mImgCategory;
        TextView mTxtName;

        public ViewHolder(View view) {
            super(view);

            mLinearParent = GenericView.findViewById(view, R.id.llParent);
            mImgCategory = GenericView.findViewById(view, R.id.iv_imgProfile);
            mTxtName = GenericView.findViewById(view, R.id.tv_txtName);

            mTxtName.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
        }
    }
}