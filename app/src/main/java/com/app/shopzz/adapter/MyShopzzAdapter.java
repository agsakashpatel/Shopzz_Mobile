package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.MyShopzzModel;
import com.app.shopzz.viewholder.MyShopzzViewHolder;

import java.util.List;

public class MyShopzzAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyShopzzModel> myShopzzModels;
    private Context context;
    private OnClickListener onClickListener;

    public MyShopzzAdapter(Context context, List<MyShopzzModel> myShopzzModels,
                           OnClickListener onClickListener) {
        this.myShopzzModels = myShopzzModels;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_ac_my_shopzz,
                        parent, false);
        return new MyShopzzViewHolder(layoutView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyShopzzModel myShopzzModel = myShopzzModels.get(position);

        final MyShopzzViewHolder myShopzzViewHolder =
                (MyShopzzViewHolder) holder;

//        Glide.with(context)
//                .load("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
//                .apply(RequestOptions.circleCropTransform())
//                .into(myShopzzViewHolder.ivStoreLogo);

        myShopzzViewHolder.llParent.setTag(position);
        myShopzzViewHolder.llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                onClickListener.onClick(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myShopzzModels.size();
    }

}
