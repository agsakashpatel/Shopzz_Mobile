package com.app.shopzz.model;

import com.app.shopzz.models.SubCategory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AGS on 08-06-2018.
 */

public class Category implements Serializable {
    private int id;
    private String categoryName;
    private String categoryImage;
    private List<SubCategory> subCategories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
