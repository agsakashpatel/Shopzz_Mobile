package com.app.shopzz.model;

/**
 * Created by AGS on 08-06-2018.
 */

public class Options {
    private int productId;
    private String combinationTitle;
    private int quantity;
    private float rate;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCombinationTitle() {
        return combinationTitle;
    }

    public void setCombinationTitle(String combinationTitle) {
        this.combinationTitle = combinationTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
