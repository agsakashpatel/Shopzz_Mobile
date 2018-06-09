package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.shopzz.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HomeViewPagerAdapter extends PagerAdapter {
    private List<String> images;
    private LayoutInflater layoutInflater;
    private Context context;

    public HomeViewPagerAdapter(Context context, List<String> images) {
        this.images = images;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = layoutInflater.inflate(R.layout.list_item_home_view_pager,
                view, false);
        ImageView ivProductImage = imageLayout
                .findViewById(R.id.ivProductImage);

        view.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
