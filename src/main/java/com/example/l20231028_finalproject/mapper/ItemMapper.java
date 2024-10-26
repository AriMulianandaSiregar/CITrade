package com.example.l20231028_finalproject.mapper;

import com.example.l20231028_finalproject.pojo.Brand;
import com.example.l20231028_finalproject.pojo.Catagory;
import com.example.l20231028_finalproject.pojo.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {
    @Select("SELECT * FROM catagory")
    @Results(id = "CatagoryMap", value = {
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "catagoryName", column = "catagoryName"),
    })
    List<Catagory> allCategoryList();

    @Select("SELECT * FROM brand")
    @Results(id = "BrandMap", value = {
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "brandName", column = "brandName"),
    })
    List<Brand> allBrandList();

    @Select("SELECT * FROM item")
    @Results(id = "ItemsMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> allItemList();

    @Select("SELECT * FROM item WHERE stock > 0")
    @Results(id = "ItemsUserMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> allItemListUser();

    @Select("select * from item WHERE item_id = #{item_id}")
    @Results(id = "ItemByIdMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    Item findItemById(int item_id);

    @Select("select * from brand WHERE brand_id = #{brand_id}")
    Brand findBrandById(int brand_id);

    @Select("select * from catagory WHERE catagory_id = #{catagory_id}")
    Catagory findCatagoryById(int catagory_id);

    @Insert("insert into Item(name,stock,price,brand_id,catagory_id,itemPic)" +
            " values(#{name},#{stock},#{price},#{brand_id},#{catagory_id},#{itemPic})")
    void addItem(Item item);

    @Delete("delete from item where item_id=#{item_id}")
    void deleteItemById(int item_id);

    @Update("update item set name=#{editItemName},stock=#{editItemStock} WHERE item_id=#{item_id}")
    void editItemById(int item_id, String editItemName, int editItemStock, double editItemPrice);

    @Update("update item set stock = stock - 1 WHERE item_id=#{item_id}")
    void purchased(int item_id);

    @Select("SELECT * FROM item WHERE name LIKE CONCAT('%', #{searchName}, '%') AND stock > 0")
    @Results(id = "ItemMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> searchItemByName(String searchName);

    @Select("SELECT * FROM item WHERE catagory_id = 1  AND stock > 0")
    @Results(id = "FoodItemsMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> allFoodItemList();

    @Select("SELECT * FROM item WHERE catagory_id = 2  AND stock > 0")
    @Results(id = "FashionItemsMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> allFashionItemList();

    @Select("SELECT * FROM item WHERE catagory_id = 3  AND stock > 0")
    @Results(id = "ElectronicItemsMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> allElectronicItemList();

    @Select("SELECT * FROM item WHERE catagory_id = 4  AND stock > 0")
    @Results(id = "SouvenirItemsMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> allSouvenirItemList();

    @Select("SELECT * FROM item WHERE catagory_id = 5  AND stock > 0")
    @Results(id = "MediaItemsMap", value = {
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "brand_id", column = "brand_id"),
            @Result(property = "catagory_id", column = "catagory_id"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<Item> allMediaItemList();
}
