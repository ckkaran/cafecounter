package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class category_data {

    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    @SerializedName("CategoryName")
    @Expose
    private String CategoryName;

    @SerializedName("tamilname")
    @Expose
    private String tamilname;

    @SerializedName("CategoryImage")
    @Expose
    private String CategoryImage;

    public category_data(String categoryId, String categoryName, String tamilname, String categoryImage) {
        this.categoryId = categoryId;
        this.CategoryName = categoryName;
        this.tamilname = tamilname;
        this.CategoryImage = categoryImage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getTamilname() {
        return tamilname;
    }

    public void setTamilname(String tamilname) {
        this.tamilname = tamilname;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }
}
