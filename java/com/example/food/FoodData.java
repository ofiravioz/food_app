package com.example.food;

public class FoodData {

    private String itemingredient;
    private String itemName;
    private String itemDescription;
    private String itemCategory;
    private String itemImage;
    private String key;

    public FoodData() {
    }

    public FoodData(String itemName, String itemDescription, String itemingredient, String itemImage,String itemCategory) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemingredient = itemingredient;
        this.itemImage = itemImage;
        this.itemCategory=itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemingredient() {
        return itemingredient;
    }
    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
