package com.app.shopzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shopzz.R;
import com.app.shopzz.listener.OnSelectAddressListener;
import com.app.shopzz.models.MyAddressesModel;
import com.app.shopzz.viewholder.MyAddressesViewHolder;

import java.util.List;

public class MyAddressesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyAddressesModel> myAddressesModels;
    private Context context;
    private OnSelectAddressListener onSelectAddressListener;

    public MyAddressesAdapter(Context context, List<MyAddressesModel> myAddressesModels,
                              OnSelectAddressListener onSelectAddressListener) {
        this.myAddressesModels = myAddressesModels;
        this.context = context;
        this.onSelectAddressListener = onSelectAddressListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_ac_my_addresses,
                        parent, false);
        return new MyAddressesViewHolder(layoutView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyAddressesModel myAddressesModel = myAddressesModels.get(position);

        final MyAddressesViewHolder myAddressesViewHolder =
                (MyAddressesViewHolder) holder;

        if (myAddressesModel.isSelected())
            myAddressesViewHolder.ivSelectAddress.setVisibility(View.VISIBLE);
        else
            myAddressesViewHolder.ivSelectAddress.setVisibility(View.INVISIBLE);

        myAddressesViewHolder.llSelectAddress.setTag(position);
        myAddressesViewHolder.llSelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unSellectAll();
                int position = (int) view.getTag();
                onSelectAddressListener.onSelectAddressClick(position);
                myAddressesModels.get(position).setSelected(true);
                notifyDataSetChanged();
            }
        });

        myAddressesViewHolder.llEditAddress.setTag(position);
        myAddressesViewHolder.llEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                onSelectAddressListener.onEditAddressClick(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myAddressesModels.size();
    }

    private void unSellectAll() {
        for (int i = 0; i < myAddressesModels.size(); i++) {
            myAddressesModels.get(i).setSelected(false);
            notifyDataSetChanged();
        }
    }
}
