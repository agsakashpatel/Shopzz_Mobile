package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopzz.BaseApplication;
import com.app.shopzz.BaseConstant;
import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.models.SubCategory;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private List<SubCategory> subCategories;
    private Context context;

    public SubCategoryAdapter(Context context, List<SubCategory> subCategories) {
        this.subCategories = subCategories;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_sub_categories, null);
        ViewHolder mh = new ViewHolder(v);
        return mh;

    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SubCategory mSubCategory = subCategories.get(position);

        holder.mTxtName.setText(mSubCategory.getCategoryName());
        holder.mLinearParent.setTag(position);

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLinearParent;
        TextView mTxtName;

        public ViewHolder(View view) {
            super(view);

            mLinearParent = GenericView.findViewById(view, R.id.llParent);
            mTxtName = GenericView.findViewById(view, R.id.tv_txtName);

            mTxtName.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_REGULAR));
        }
    }
}
