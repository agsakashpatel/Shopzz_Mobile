package com.app.shopzz.core;

import android.support.v4.app.Fragment;

public class FragmentNavigationInfo {

    private Fragment fragment;

    public FragmentNavigationInfo(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}