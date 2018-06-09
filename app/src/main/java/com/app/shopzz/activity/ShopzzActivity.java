package com.app.shopzz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.app.shopzz.R;
import com.app.shopzz.core.FooterSelection;
import com.app.shopzz.core.FragmentNavigationInfo;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.fragment.CartFragment;
import com.app.shopzz.fragment.HomeFragment;
import com.app.shopzz.fragment.SearchFragment;
import com.app.shopzz.listener.IViewClick;

import java.util.Stack;

/**
 * Created by AGS on 08-05-2018.
 */

public class ShopzzActivity extends FragmentActivity implements IViewClick {

    public IViewClick onClick;
    private Stack<FragmentNavigationInfo> navigationStack = new Stack<>();

    private FooterSelection footerSelection = FooterSelection.HOME;

    private ImageView mImgHome;
    private ImageView mImgSearch;
    private ImageView mImgCart;
    private ImageView mImgAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_shopzz);

        mImgHome = GenericView.findViewById(this, R.id.iv_imgHome);
        mImgSearch = GenericView.findViewById(this, R.id.iv_imgSearch);
        mImgCart = GenericView.findViewById(this, R.id.iv_imgCart);
        mImgAccount = GenericView.findViewById(this, R.id.iv_imgAccount);

        loadFragment(new HomeFragment());

    }

    private void loadFragment(Fragment fragment) {
        onClick = (IViewClick) fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentNavigationInfo navigationInfo = new FragmentNavigationInfo(fragment);
        fragmentTransaction.replace(R.id.flContainer, navigationInfo.getFragment(), "").commit();
        addToStackEntry(navigationInfo);
    }

    /**
     * @param fragment ({@link Fragment}):  get Current fragment
     */
    public void addFragment(Fragment fragment) {
        onClick = (IViewClick) fragment;
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);

        FragmentNavigationInfo navigationInfo = new FragmentNavigationInfo(fragment);
        fragmentTransaction.replace(R.id.flContainer, navigationInfo.getFragment(), "").commit();
        addToStackEntry(navigationInfo);
    }

    public void addToStackEntry(FragmentNavigationInfo navigationInfo) {
        navigationStack.push(navigationInfo);
    }

    @Override
    public void onViewClick(View v) {
        onClick.onViewClick(v);

        switch (v.getId()) {
            case R.id.iv_imgHome:
                footerSelection = FooterSelection.HOME;
                loadFragment(new HomeFragment());
                break;
            case R.id.iv_imgSearch:
                footerSelection = FooterSelection.SEARCH;
                loadFragment(new SearchFragment());
                break;
            case R.id.iv_imgCart:
                footerSelection = FooterSelection.CART;
                loadFragment(new CartFragment());
                break;
            case R.id.iv_imgAccount:
//                footerSelection = FooterSelection.ACCOUNT;

                Intent mIntent = new Intent(this, LoginActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
        changeSelection();

    }

    private void changeSelection() {
        switch (footerSelection) {
            case HOME:
                mImgHome.setImageResource(R.mipmap.home_active);
                mImgSearch.setImageResource(R.mipmap.search_normal);
                mImgCart.setImageResource(R.mipmap.cart_normal);
                mImgAccount.setImageResource(R.mipmap.my_account_normal);
                break;
            case SEARCH:
                mImgHome.setImageResource(R.mipmap.home_normal);
                mImgSearch.setImageResource(R.mipmap.search_active);
                mImgCart.setImageResource(R.mipmap.cart_normal);
                mImgAccount.setImageResource(R.mipmap.my_account_normal);
                break;
            case CART:
                mImgHome.setImageResource(R.mipmap.home_normal);
                mImgSearch.setImageResource(R.mipmap.search_normal);
                mImgCart.setImageResource(R.mipmap.cart_active);
                mImgAccount.setImageResource(R.mipmap.my_account_normal);
                break;
            case ACCOUNT:
                mImgHome.setImageResource(R.mipmap.home_normal);
                mImgSearch.setImageResource(R.mipmap.search_normal);
                mImgCart.setImageResource(R.mipmap.cart_normal);
                mImgAccount.setImageResource(R.mipmap.my_account_active);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            if (!navigationStack.isEmpty()) {
                getSupportFragmentManager().popBackStack();
                navigationStack.pop();
                if (!navigationStack.isEmpty()) {
                    FragmentNavigationInfo newNavigationInfo = navigationStack.peek();
                    onClick = (IViewClick) newNavigationInfo.getFragment();
                }
            }
        } else {
            super.onBackPressed();
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }
    }
}