package com.example.l20231028_finalproject.service;

import com.example.l20231028_finalproject.pojo.Brand;
import com.example.l20231028_finalproject.pojo.Catagory;
import com.example.l20231028_finalproject.pojo.Item;

import java.util.List;

public interface ItemService {
    List<Catagory> allCategoryList();
    List<Brand> allBrandList();
    List<Item> allItemList();
    List<Item> allItemListUser();
    void addItem(Item item);
    Item findItemById(int item_id);
    Brand findBrandById(int brand_id);
    void deleteItemById(int item_id);
    void editItemById(int item_id, String editItemName, int editItemStock, double editItemPrice);

    void purchased(int item_id);

    List<Item> searchItemByName(String searchName);

    List<Item> allFoodItemList();
    List<Item> allFashionItemList();
    List<Item> allElectronicItemList();
    List<Item> allSouvenirItemList();
    List<Item> allMediaItemList();
    Catagory findCatagoryById(int catagory_id);
}
