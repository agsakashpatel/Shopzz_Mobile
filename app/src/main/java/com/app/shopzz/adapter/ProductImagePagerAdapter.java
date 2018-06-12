package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.app.shopzz.R;
import com.app.shopzz.api.ServerConfig;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.IconPagerAdapter;
import com.app.shopzz.model.Images;
import com.app.shopzz.utility.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AGS on 11-06-2018.
 */

public class ProductImagePagerAdapter extends PagerAdapter implements IconPagerAdapter {
    private Context context;
    private List<Images> imageList;
    private int[] screenWH;

    public ProductImagePagerAdapter(Context mContext, List<Images> mImageList) {
        this.context = mContext;
        this.imageList = mImageList;
        screenWH = Utils.getInstance().getScreenWidthHeight();
    }

    @Override
    public int getIconResId(int index) {
        if (imageList.get(index).getFileName().endsWith(".JPG")
                || imageList.get(index).getFileName().endsWith(".PNG")
                || imageList.get(index).getFileName().endsWith(".jpg")
                || imageList.get(index).getFileName().endsWith(".png"))
        {
            return R.mipmap.ic_circle_normal;
        }
        else {
            return R.mipmap.ic_video_normal;
        }
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
        ImageView imageView = GenericView.findViewById(itemView, R.id.iv_imgProfile);
        final ProgressBar mProgress = GenericView.findViewById(itemView, R.id.progressbar);

        if (!imageList.get(position).getFileName().equalsIgnoreCase("")) {
            Picasso.with(context).load(ServerConfig.PRODUCT_IMAGE + imageList.get(position).getFileName()).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    mProgress.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    mProgress.setVisibility(View.GONE);
                }
            });
        } else {
            mProgress.setVisibility(View.GONE);
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        ((ViewPager) container).addView(itemView, 0);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}