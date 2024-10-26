package com.example.l20231028_finalproject.mapper;

import com.example.l20231028_finalproject.pojo.Item;
import com.example.l20231028_finalproject.pojo.Transaction;
import com.example.l20231028_finalproject.pojo.TransactionDTO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TransactionMapper {
    @Insert("insert into transaction(transactionDate,paymentStatus,customer_id,location_id,item_id)" +
            " values(#{transactionDate},#{paymentStatus},#{customer_id},#{location_id},#{item_id})")
    void addToCart(Transaction transaction);

    @Select("SELECT " +
            "t.transaction_id, " +
            "t.transactionDate, " +
            "t.paymentStatus, " +
            "c.customer_id, " +
            "l.country, " +
            "l.city, " +
            "i.item_id, " +
            "i.name, " +
            "i.price, " +
            "i.itemPic " +
            "FROM Transaction t " +
            "INNER JOIN customer c ON t.customer_id = c.customer_id " +
            "INNER JOIN Location l ON t.location_id = l.location_id " +
            "INNER JOIN Item i ON t.item_id = i.item_id " +
            "WHERE c.customer_id = #{user_id} AND t.paymentStatus = 'unpaid' ")
    @Results(id = "unpaidTransactionsMap", value = {
            @Result(property = "transaction_id", column = "transaction_id"),
            @Result(property = "transactionDate", column = "transactionDate"),
            @Result(property = "paymentStatus", column = "paymentStatus"),
            @Result(property = "country", column = "country"),
            @Result(property = "city", column = "city"),
            @Result(property = "customer_id", column = "user_id"),
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<TransactionDTO> getAllUnpaidTransactions(int user_id);

    @Select("SELECT " +
            "t.transaction_id, " +
            "t.transactionDate, " +
            "t.paymentStatus, " +
            "c.customer_id, " +
            "l.country, " +
            "l.city, " +
            "i.item_id, " +
            "i.name, " +
            "i.price, " +
            "i.itemPic " +
            "FROM Transaction t " +
            "INNER JOIN customer c ON t.customer_id = c.customer_id " +
            "INNER JOIN Location l ON t.location_id = l.location_id " +
            "INNER JOIN Item i ON t.item_id = i.item_id " +
            "WHERE c.customer_id = #{user_id} AND t.paymentStatus = 'paid' ")
    @Results(id = "paidTransactionsMap", value = {
            @Result(property = "transaction_id", column = "transaction_id"),
            @Result(property = "transactionDate", column = "transactionDate"),
            @Result(property = "paymentStatus", column = "paymentStatus"),
            @Result(property = "country", column = "country"),
            @Result(property = "city", column = "city"),
            @Result(property = "customer_id", column = "user_id"),
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    List<TransactionDTO> getAllpaidTransactions(int user_id);

    @Select("SELECT " +
            "t.transaction_id, " +
            "t.transactionDate, " +
            "t.paymentStatus, " +
            "l.country, " +
            "l.city, " +
            "i.item_id, " +
            "i.name, " +
            "i.price, " +
            "i.itemPic " +
            "FROM Transaction t " +
            "INNER JOIN Location l ON t.location_id = l.location_id " +
            "INNER JOIN Item i ON t.item_id = i.item_id " +
            "WHERE t.transaction_id = #{transaction_id}")
    @Results(id = "payNowMap", value = {
            @Result(property = "transaction_id", column = "transaction_id"),
            @Result(property = "transactionDate", column = "transactionDate"),
            @Result(property = "paymentStatus", column = "paymentStatus"),
            @Result(property = "country", column = "country"),
            @Result(property = "country", column = "city"),
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    TransactionDTO paynow(int transaction_id);

    @Select("SELECT " +
            "t.transaction_id, " +
            "t.transactionDate, " +
            "t.paymentStatus, " +
            "l.country, " +
            "l.city, " +
            "i.item_id, " +
            "i.name, " +
            "i.price, " +
            "i.itemPic " +
            "FROM Transaction t " +
            "INNER JOIN Location l ON t.location_id = l.location_id " +
            "INNER JOIN Item i ON t.item_id = i.item_id " +
            "WHERE t.transaction_id = #{transaction_id}")
    @Results(id = "detailTransactionMap", value = {
            @Result(property = "transaction_id", column = "transaction_id"),
            @Result(property = "transactionDate", column = "transactionDate"),
            @Result(property = "paymentStatus", column = "paymentStatus"),
            @Result(property = "country", column = "country"),
            @Result(property = "country", column = "city"),
            @Result(property = "item_id", column = "item_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "itemPic", column = "itemPic"),
    })
    TransactionDTO detailHistory(int transaction_id);

    @Update("update transaction set paymentStatus= 'paid', transactionDate = now() WHERE transaction_id=#{transaction_id}")
    void checkout(int transaction_id);

    @Delete("delete from transaction where transaction_id=#{transaction_id}")
    void deleteTransactionById(int transaction_id);

}
