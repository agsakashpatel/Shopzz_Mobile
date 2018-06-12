package com.app.shopzz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.app.shopzz.activity.ShopzzActivity;
import com.app.shopzz.adapter.SubCategoryAdapter;
import com.app.shopzz.api.ServerConfig;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.IViewClick;
import com.app.shopzz.model.Category;
import com.app.shopzz.utility.Debug;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by AGS on 09-06-2018.
 */

public class SubCategoryFragment extends Fragment implements IViewClick {
    private ShopzzActivity parent;

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;

    private RecyclerView mRecyclerSubCategory;
    private LinearLayout mLinearParent;
    private LinearLayoutManager mLinearManager;

    SubCategoryAdapter mSubCategoryAdapter;
    Category mCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parent = (ShopzzActivity) getActivity();
        mCategory = (Category) getArguments().get("subCategory");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_subcategory, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ivClose = GenericView.findViewById(getView(), R.id.ivClose);
        tvTitle = GenericView.findViewById(getView(), R.id.tvTitle);
        tvEdit = GenericView.findViewById(getView(), R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.back_blue);
        tvTitle.setText(mCategory.getCategoryName().toString());
        tvTitle.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
        tvEdit.setVisibility(View.INVISIBLE);

        mLinearParent = GenericView.findViewById(getView(), R.id.linearParent);
        mRecyclerSubCategory = GenericView.findViewById(getView(), R.id.recyclerSubCategory);

       /* if (mCategory.getCategoryImage()!=null) {
            Glide.with(parent).load(ServerConfig.CATEGORY_IMAGE
                    +mCategory.getCategoryImage())
                    .placeholder(R.drawable.splash_img)
                    .into(mLinearParent);
        }*/

        mLinearManager = new LinearLayoutManager(parent);
        mRecyclerSubCategory.setLayoutManager(mLinearManager);
        mSubCategoryAdapter = new SubCategoryAdapter(parent, mCategory.getSubCategories());
        mRecyclerSubCategory.setAdapter(mSubCategoryAdapter);

    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.llParent:
                int position = Integer.parseInt(v.getTag().toString());
                Debug.trace("Position: " + position);
                break;
        }
    }

   /* public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                break;
        }
    }*/



}
