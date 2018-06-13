package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shopzz.BaseApplication;
import com.app.shopzz.BaseConstant;
import com.app.shopzz.R;
import com.app.shopzz.api.ServerConfig;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.indicator.IconPageIndicator;
import com.app.shopzz.model.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AGS on 09-06-2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private Context context;
    private List<Products> productList;

    private boolean isShowLoading;
    private ProductImagePagerAdapter imageAdapter;

    public ProductAdapter(Context mContext, List<Products> mProductList, boolean isShowLoading) {
        this.context = mContext;
        this.productList = mProductList;
        this.isShowLoading = isShowLoading;
    }

    public void isShowLoading(boolean isShowLoading) {
        this.isShowLoading = isShowLoading;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(context).inflate(R.layout.raw_footer, parent, false);
            return new FooterViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_products, parent, false);
            return new ViewHolder(v);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position) {
        return position == productList.size();
    }

    @Override
    public int getItemCount() {
        return productList.size() + 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            if (isShowLoading) {
                footerHolder.mLinearLoading.setVisibility(View.VISIBLE);
            } else {
                footerHolder.mLinearLoading.setVisibility(View.GONE);
            }
        } else if (holder instanceof ViewHolder) {
            Products mProduct = productList.get(position);

            ViewHolder genericViewHolder = (ViewHolder) holder;

            if (mProduct.getType().equalsIgnoreCase("product")) {
                genericViewHolder.mRelativeEvent.setVisibility(View.GONE);
                genericViewHolder.mRelativeProduct.setVisibility(View.VISIBLE);

                imageAdapter = new ProductImagePagerAdapter(context, mProduct.getProductImages());
                genericViewHolder.mPager.setAdapter(imageAdapter);
                genericViewHolder.pageIndicator.setViewPager(genericViewHolder.mPager);

                genericViewHolder.mTxtName.setText(mProduct.getProductTitle());
                genericViewHolder.mTxtStore.setText(context.getResources().getString(R.string.str_by) + " " + mProduct.getStoreName());

                if (!mProduct.getOptions().isEmpty()) {
                    genericViewHolder.mTxtPrice.setText(mProduct.getOptions().get(0).getRate() + " " + context.getResources().getString(R.string.str_kd));
                }
            } else if (mProduct.getType().equalsIgnoreCase("event")) {
                genericViewHolder.mRelativeEvent.setVisibility(View.VISIBLE);
                genericViewHolder.mRelativeProduct.setVisibility(View.GONE);

                Picasso.with(context).load(ServerConfig.EVENT_IMAGE + mProduct.getImage()).into(genericViewHolder.mImgEvent);
                genericViewHolder.mTxtEventName.setText(mProduct.getTitle());
                genericViewHolder.mTxtEventLocation.setText(mProduct.getAddress());

                if (mProduct.getEventType().equalsIgnoreCase("location")) {
                    genericViewHolder.mTxtTrack.setText(context.getResources().getString(R.string.str_track));
                } else {
                    genericViewHolder.mTxtTrack.setText(context.getResources().getString(R.string.str_call_me));
                }
            }
            genericViewHolder.mImgFavourite.setTag(position);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtLoading;
        ProgressBar mProgress;
        LinearLayout mLinearLoading;


        public FooterViewHolder(View itemView) {
            super(itemView);
            mLinearLoading = GenericView.findViewById(itemView, R.id.llLoading);
            mTxtLoading = GenericView.findViewById(itemView, R.id.tv_txtLoading);
            mProgress = GenericView.findViewById(itemView, R.id.pb_progress);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mRelativeProduct;
        private ViewPager mPager;
        private IconPageIndicator pageIndicator;
        private ImageView mImgFavourite;
        private TextView mTxtName;
        private TextView mTxtStore;
        private TextView mTxtPrice;


        private RelativeLayout mRelativeEvent;
        private ImageView mImgEvent;
        private TextView mTxtEventName;
        private TextView mTxtEventLocation;
        private TextView mTxtTrack;

        public ViewHolder(View view) {
            super(view);

            mRelativeProduct = GenericView.findViewById(itemView, R.id.rl_Product);
            mImgFavourite = GenericView.findViewById(itemView, R.id.iv_imgFavourite);
            mPager = GenericView.findViewById(view, R.id.pager);
            pageIndicator = GenericView.findViewById(itemView, R.id.indicator);
            mTxtName = GenericView.findViewById(view, R.id.tv_txtName);
            mTxtStore = GenericView.findViewById(view, R.id.tv_txtStore);
            mTxtPrice = GenericView.findViewById(view, R.id.tv_txtPrice);

            mRelativeEvent = GenericView.findViewById(itemView, R.id.rl_event);
            mImgEvent = GenericView.findViewById(itemView, R.id.iv_imgEvent);
            mTxtEventName = GenericView.findViewById(itemView, R.id.tv_txtEventName);
            mTxtEventLocation = GenericView.findViewById(itemView, R.id.tv_txtEventLocation);
            mTxtTrack = GenericView.findViewById(itemView, R.id.tv_txtTrack);

            mTxtName.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
            mTxtStore.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_REGULAR));
            mTxtPrice.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));

            mTxtEventName.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
            mTxtEventLocation.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_REGULAR));
            mTxtTrack.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
        }
    }
}