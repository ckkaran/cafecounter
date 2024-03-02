package com.example.foodpos_counter.Model;

import java.util.ArrayList;

public class ItemSampleModel {
    private String id;
    private String name;
    private String image;
    private ArrayList<ItemsModel> items;

    public ItemSampleModel(String id, String name, String image, ArrayList<ItemsModel> items) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ItemsModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemsModel> items) {
        this.items = items;
    }
}
