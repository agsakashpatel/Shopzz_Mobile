package com.app.shopzz.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopzz.BaseApplication;
import com.app.shopzz.BaseConstant;
import com.app.shopzz.R;
import com.app.shopzz.customView.GenericView;
import com.app.shopzz.listener.OnDialogButtonClick;
import com.app.shopzz.listener.OnValidationClick;

/**
 * Created by AGS on 10-05-2018.
 */

public class ToastHelper {
    private static int duration = Toast.LENGTH_LONG;
    private static Toast toast = null;

    public static void displayInfo(final String message) {

        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(BaseApplication.getInstance(), message, duration);
        toast.show();
    }

    public static void displayInfo(final String message, int gravity) {

        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(BaseApplication.getInstance(), message, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    public static void displayCustomToast(Activity mContext, String message, final View mView,
                                          final OnValidationClick onValidationClick) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_validation_alert, null);
        alertDialog.setView(dialogView);

        final AlertDialog dialog = alertDialog.create();

        TextView mTxtTitle = GenericView.findViewById(dialogView, R.id.tv_txtTitle);
        Button mBtnOk = GenericView.findViewById(dialogView, R.id.bt_btnOk);
        mTxtTitle.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_REGULAR));
        mBtnOk.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));

        mTxtTitle.setText(message);
        mBtnOk.setText(mContext.getResources().getString(R.string.str_ok));
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onValidationClick.OnValidationClick(mView);
            }
        });

        dialog.show();
    }

    public static void displayCustomToast(Activity mContext, String message) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_validation_alert, null);
        alertDialog.setView(dialogView);

        final AlertDialog dialog = alertDialog.create();

        TextView mTxtTitle = GenericView.findViewById(dialogView, R.id.tv_txtTitle);
        Button mBtnOk = GenericView.findViewById(dialogView, R.id.bt_btnOk);
        mTxtTitle.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_REGULAR));
        mBtnOk.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));

        mTxtTitle.setText(message);
        mBtnOk.setText(mContext.getResources().getString(R.string.str_ok));
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void displayCustomDialog(Activity mContext, String title, String message, final View mView,
                                           final OnDialogButtonClick onDialogButtonClick) {
        final Dialog alertDialog = new Dialog(mContext, R.style.AlertDialogTheme);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_for_alert_message, null);

        TextView tvDialogTitle = GenericView.findViewById(dialogView, R.id.tvDialogTitle);
        TextView tvDialogMessage = GenericView.findViewById(dialogView, R.id.tvDialogMessage);
        TextView tvCancle = GenericView.findViewById(dialogView, R.id.tvCancle);
        TextView tvYes = GenericView.findViewById(dialogView, R.id.tvYes);

        tvDialogTitle.setText(title);
        tvDialogMessage.setText(message);

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                onDialogButtonClick.OnCancleClick(mView);
            }
        });
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                onDialogButtonClick.OnYesClick(mView);
            }
        });

        alertDialog.setContentView(dialogView);
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    public static void dialogAddToCart(Activity mContext, String title, String message, final View mView,
                                       final OnDialogButtonClick onDialogButtonClick) {
        final Dialog alertDialog = new Dialog(mContext, R.style.AlertDialogTheme);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_product_popup, null);

        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.getWindow().setAttributes(params);

        ImageView ivClose = GenericView.findViewById(dialogView, R.id.ivClose);
        TextView tvSelectProductColor = GenericView.findViewById(dialogView, R.id.tvSelectProductColor);
        TextView tvSelectProductQuantity = GenericView.findViewById(dialogView, R.id.tvSelectProductQuantity);
        TextView tvAddToCart = GenericView.findViewById(dialogView, R.id.tvAddToCart);

        alertDialog.setContentView(dialogView);
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    public static void showDialog(Context mContext, String title, String message, String buttonText1, View.OnClickListener onClickListener1, String buttonText2, View.OnClickListener onClickListener2, boolean isDualButton) {
        Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = LayoutInflater.from(mContext).inflate(R.layout.diag_alert, null);

        TextView mTxtTitle = GenericView.findViewById(view, R.id.tv_txtTitle);
        TextView mTxtMessage = GenericView.findViewById(view, R.id.tv_txtMessage);

        Button mBtnButton1 = GenericView.findViewById(view, R.id.bt_btnButton1);
        Button mBtnButton2 = GenericView.findViewById(view, R.id.bt_btnButton2);
        Button mBtnButton3 = GenericView.findViewById(view, R.id.bt_btnButton3);

        LinearLayout mLinearButton = GenericView.findViewById(view, R.id.ll_linearButton);

        mTxtTitle.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
        mTxtMessage.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_REGULAR));

        mBtnButton1.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
        mBtnButton2.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));
        mBtnButton3.setTypeface(BaseApplication.mTypefaceMap.get(BaseConstant.LATO_BOLD));

        mTxtTitle.setText(title);
        mTxtMessage.setText(message);
        mBtnButton1.setText(buttonText2);
        mBtnButton2.setText(buttonText1);

        if (isDualButton) {
            mLinearButton.setVisibility(View.VISIBLE);
            mBtnButton3.setVisibility(View.GONE);
        } else {
            mLinearButton.setVisibility(View.GONE);
            mBtnButton3.setVisibility(View.VISIBLE);
        }

        mBtnButton1.setOnClickListener(onClickListener1);
        mBtnButton2.setOnClickListener(onClickListener2);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.show();
    }
}