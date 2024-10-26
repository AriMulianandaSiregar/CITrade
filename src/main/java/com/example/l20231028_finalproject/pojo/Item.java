package com.example.l20231028_finalproject.pojo;

import lombok.Data;

@Data
public class Item {
    private int item_id;
    private String name;
    private int stock;
    private double price;
    private int brand_id;
    private int catagory_id;
    private String itemPic;
    private String brandName;
}
