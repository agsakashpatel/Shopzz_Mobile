package com.app.shopzz.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopzz.R;
import com.app.shopzz.adapter.MyShopzzAdapter;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.OnClickListener;
import com.app.shopzz.models.MyShopzzModel;

import java.util.ArrayList;
import java.util.List;

public class MyShopzzActivity extends AppCompatActivity implements OnClickListener {

    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvEdit;
    private RecyclerView recyclerViewGrid;

    List<MyShopzzModel> myShopzzModels;
    MyShopzzAdapter myShopzzAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_shopzz);

        ivClose = GenericView.findViewById(this, R.id.ivClose);
        tvTitle = GenericView.findViewById(this, R.id.tvTitle);
        tvEdit = GenericView.findViewById(this, R.id.tvEdit);
        ivClose.setImageResource(R.mipmap.back_blue);
        tvTitle.setText(getResources().getString(R.string.str_my_shopzz));
        tvEdit.setVisibility(View.INVISIBLE);

        recyclerViewGrid = GenericView.findViewById(this, R.id.recyclerViewGrid);
        setListData();
        setList();
    }

    private void setListData() {
        myShopzzModels = new ArrayList<>();
        myShopzzAdapter = new MyShopzzAdapter(this, myShopzzModels, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGrid.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGrid.setAdapter(myShopzzAdapter);
    }

    private void setList() {
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzModels.add(new MyShopzzModel("hdfh"));
        myShopzzAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(int position) {

    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
