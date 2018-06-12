package com.app.shopzz.listener;

/**
 * Created by AGS on 11-06-2018.
 */

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}