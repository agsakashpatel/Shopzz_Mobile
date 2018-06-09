package com.app.shopzz.customView;

import android.app.Dialog;
import android.content.Context;

import com.app.shopzz.R;


/**
 * Created by comp252 on 29-05-2017.
 */

public class CustomDialog {
    private static CustomDialog instance;
    private Dialog dialog;

    private CustomDialog() {
    }

    public static CustomDialog getInstance() {
        if (instance == null) {
            instance = new CustomDialog();
        }
        return instance;
    }

    /**
     * Show progress bar...
     *
     * @param mContext : the context in which the dialog should run
     */
    public void show(Context mContext) {
        dialog = new Dialog(mContext, R.style.DialogTheme);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.item_loader);

        try {
            if (dialog != null) {
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public boolean isDialogShowing() {
        if (dialog != null && dialog.isShowing()) {
            return dialog.isShowing();
        } else {
            return false;
        }
    }
}