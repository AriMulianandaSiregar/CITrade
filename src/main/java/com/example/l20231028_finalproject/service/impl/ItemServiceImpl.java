package com.example.l20231028_finalproject.service.impl;

import com.example.l20231028_finalproject.mapper.ItemMapper;
import com.example.l20231028_finalproject.pojo.Brand;
import com.example.l20231028_finalproject.pojo.Catagory;
import com.example.l20231028_finalproject.pojo.Item;
import com.example.l20231028_finalproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Catagory> allCategoryList() {
        List<Catagory> allCatagoryList = itemMapper.allCategoryList();
        return allCatagoryList;
    }
    @Override
    public List<Brand> allBrandList() {
        List<Brand> allBrandList = itemMapper.allBrandList();
        return allBrandList;
    }
    @Override
    public List<Item> allItemList() {
        List<Item> allItemList = itemMapper.allItemList();
        return allItemList;
    }

    @Override
    public List<Item> allItemListUser() {
        List<Item> allItemList = itemMapper.allItemListUser();
        return allItemList;
    }

    @Override
    public void addItem(Item item) {
        itemMapper.addItem(item);
    }

    @Override
    public Item findItemById(int item_id) {
        Item item = itemMapper.findItemById(item_id);
        return item;
    }

    @Override
    public Brand findBrandById(int brand_id) {
        return itemMapper.findBrandById(brand_id);
    }
    @Override
    public void deleteItemById(int item_id) {
        itemMapper.deleteItemById(item_id);
    }
    @Override
    public void editItemById(int item_id, String editItemName, int editItemStock, double editItemPrice) {
        itemMapper.editItemById(item_id, editItemName, editItemStock, editItemPrice);
    }

    @Override
    public void purchased(int item_id) {
        itemMapper.purchased(item_id);
    }

    @Override
    public List<Item> searchItemByName(String searchName) {
        List<Item> itemList = itemMapper.searchItemByName(searchName);
        return itemList;
    }
    @Override
    public List<Item> allFoodItemList() {
        List<Item> itemList = itemMapper.allFoodItemList();
        return itemList;
    }
    @Override
    public List<Item> allFashionItemList() {
        List<Item> itemList = itemMapper.allFashionItemList();
        return itemList;
    }
    @Override
    public List<Item> allElectronicItemList() {
        List<Item> itemList = itemMapper.allElectronicItemList();
        return itemList;
    }
    @Override
    public List<Item> allSouvenirItemList() {
        List<Item> itemList = itemMapper.allSouvenirItemList();
        return itemList;
    }
    @Override
    public List<Item> allMediaItemList() {
        List<Item> itemList = itemMapper.allMediaItemList();
        return itemList;
    }

    @Override
    public Catagory findCatagoryById(int catagory_id) {
        Catagory catagory = itemMapper.findCatagoryById(catagory_id);
        return catagory;
    }
}
