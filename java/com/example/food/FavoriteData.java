package com.example.food;

public class FavoriteData {

    private String itemName;
    private String url;
    private String key;

    public FavoriteData() {
    }

    public FavoriteData(String itemName, String url) {
        this.itemName = itemName;
        this.url = url;

    }

    public String getItemName() {
        return itemName;
    }

    public String geturl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
