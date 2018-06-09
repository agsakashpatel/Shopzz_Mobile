package com.app.shopzz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.app.shopzz.R;
import com.app.shopzz.activity.ShopzzActivity;
import com.app.shopzz.adapter.CategoryAdapter;
import com.app.shopzz.api.ApiList;
import com.app.shopzz.api.RequestCode;
import com.app.shopzz.api.RequestListener;
import com.app.shopzz.api.RestClient;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.IViewClick;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.model.Category;
import com.app.shopzz.model.Products;
import com.app.shopzz.utility.Debug;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AGS on 08-05-2018.
 */

public class HomeFragment extends Fragment implements IViewClick, OnClickListener, RequestListener {
    private ShopzzActivity parent;

    private CategoryAdapter mCategoryAdapter;

    private List<Category> categoryList = new ArrayList<>();
    private List<Products> productList = new ArrayList<>();

    private RecyclerView mRecycleCategory;
    private LinearLayoutManager mLinearManager;

    private RecyclerView mRecycleProducts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (ShopzzActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecycleCategory = GenericView.findViewById(getView(), R.id.recycleCategory);
        mRecycleProducts = GenericView.findViewById(getView(), R.id.recycleProducts);

        mLinearManager = new LinearLayoutManager(parent, LinearLayoutManager.HORIZONTAL, false);
        mRecycleCategory.setLayoutManager(mLinearManager);

        mLinearManager = new LinearLayoutManager(parent);
        mRecycleProducts.setLayoutManager(mLinearManager);

        getCategoryList();
    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.llParent:
                int position = Integer.parseInt(v.getTag().toString());
                Debug.trace("Position: " + position);

                SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("subCategory", categoryList.get(position));
                subCategoryFragment.setArguments(mBundle);

                parent.addFragment(subCategoryFragment);
                break;
        }
    }

    @Override
    public void onClick(int position) {

    }

    private void getCategoryList() {
        JSONObject param = new JSONObject();
        RestClient.getInstance().post(parent, Request.Method.GET, ApiList.APIs.getCategoryList.getUrl(), param,
                this, RequestCode.CATEGORY, true, false);
    }

    private void getProductList() {
        try {
            JSONObject param = new JSONObject();
            param.put(ApiList.KEY_USER_ID, "");
            param.put(ApiList.KEY_PAGE, 1);
            RestClient.getInstance().post(parent, Request.Method.POST, ApiList.APIs.getProductList.getUrl(), param,
                    this, RequestCode.PRODUCTS, true, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete(RequestCode requestCode, Object object, String message) {
        switch (requestCode) {
            case CATEGORY:
                categoryList = (List<Category>) object;

                if (!categoryList.isEmpty()) {
                    mCategoryAdapter = new CategoryAdapter(parent, categoryList);
                    mRecycleCategory.setAdapter(mCategoryAdapter);
                }

                getProductList();
                break;
            case PRODUCTS:
                productList.addAll((List<Products>) object);
                Debug.trace("Size:" + productList.size());


                break;
        }
    }

    @Override
    public void onException(int statusCode, String error, RequestCode requestCode) {
    }

    @Override
    public void onRetryRequest(RequestCode requestCode) {
    }
}