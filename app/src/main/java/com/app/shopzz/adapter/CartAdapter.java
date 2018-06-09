package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnCartClickListener;
import com.app.shopzz.models.CartModel;
import com.app.shopzz.viewholder.CartViewHolder;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<CartModel> cartModels;
    private Context context;
    private OnCartClickListener onCartClickListener;
    private boolean isEditMode;

    public CartAdapter(Context context, List<CartModel> cartModels,
                       OnCartClickListener onCartClickListener) {
        this.cartModels = cartModels;
        this.context = context;
        this.onCartClickListener = onCartClickListener;
        isEditMode = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ac_cart,
                        parent, false);
        return new CartViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartModel cartModel = cartModels.get(position);

        final CartViewHolder cartViewHolder = (CartViewHolder) holder;

        if (cartModel.isSelected())
            cartViewHolder.cbRemove.setBackgroundResource(R.mipmap.radio_selected);
        else
            cartViewHolder.cbRemove.setBackgroundResource(R.mipmap.radio_normal);

        if (isEditMode()) {
            cartViewHolder.cbRemove.setVisibility(View.VISIBLE);
            cartViewHolder.rlSelectProductColorAndQuantity.setVisibility(View.VISIBLE);
            cartViewHolder.tvProductColor.setVisibility(View.GONE);
            cartViewHolder.tvQuantity.setVisibility(View.GONE);
            cartViewHolder.tvMultiply.setVisibility(View.GONE);
        } else {
            cartViewHolder.cbRemove.setVisibility(View.GONE);
            cartViewHolder.rlSelectProductColorAndQuantity.setVisibility(View.GONE);
            cartViewHolder.tvProductColor.setVisibility(View.VISIBLE);
            cartViewHolder.tvQuantity.setVisibility(View.VISIBLE);
            cartViewHolder.tvMultiply.setVisibility(View.VISIBLE);
        }

        cartViewHolder.llParent.setTag(position);
        cartViewHolder.llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                onCartClickListener.onClick(position);
            }
        });

        cartViewHolder.cbRemove.setTag(position);
        cartViewHolder.cbRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                if (cartModels.get(position).isSelected()) {
                    cartModels.get(position).setSelected(false);
                } else {
                    cartModels.get(position).setSelected(true);
                }
                onCartClickListener.onCheckboxClick(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public boolean isEditMode() {
        return isEditMode;
    }

    public void setEditMode(boolean editMode) {
        unSelectAll();
        isEditMode = editMode;
    }

    private void unSelectAll() {
        try {
            for (int i = 0; i < cartModels.size(); i++) {
                cartModels.get(i).setSelected(false);
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("unselectall", e.getMessage());
        }
    }

    public int getSelectionCount() {
        int i = 0;
        for (int j = 0; j < cartModels.size(); j++) {
            if (cartModels.get(j).isSelected())
                i++;
        }
        return i;
    }
}