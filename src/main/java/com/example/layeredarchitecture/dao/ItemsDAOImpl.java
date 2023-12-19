package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemsDAOImpl implements ItemsDAO{

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<ItemDTO> getAllItems = new ArrayList<>();

        while (resultSet.next()){
            ItemDTO itemDTO = new ItemDTO(
                    resultSet.getString("code"),
                    resultSet.getString("description"),
                    resultSet.getBigDecimal("unitPrice"),
                    resultSet.getInt("qtyOnHand")
            );
            getAllItems.add(itemDTO);
        }
        return getAllItems;
    }

    @Override
    public boolean itemSaveOnAction(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand()
        );
    }

    @Override
    public boolean itemUpdateOnAction(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand(),
                itemDTO.getCode()
        );
    }

    @Override
    public boolean itemDeleteOnAction(String code) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Item WHERE code=?",
                code
        );
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SQLUtil.execute("SELECT code FROM Item WHERE code=?", code);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");

        if (resultSet.next()) {
            String id = resultSet.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO findItem(String newItemCode) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Item WHERE code=?");
        resultSet.next();
        return new ItemDTO(newItemCode + "", resultSet.getString("description"), resultSet.getBigDecimal("unitPrice"), resultSet.getInt("qtyOnHand"));
    }
}
