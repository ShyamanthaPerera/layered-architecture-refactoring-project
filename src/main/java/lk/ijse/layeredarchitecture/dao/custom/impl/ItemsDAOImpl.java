package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SQLUtil;
import lk.ijse.layeredarchitecture.dao.custom.ItemsDAO;
import lk.ijse.layeredarchitecture.entity.ItemEntity;

import java.sql.*;
import java.util.ArrayList;

public class ItemsDAOImpl implements ItemsDAO {

    @Override
    public ArrayList<ItemEntity> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<ItemEntity> getAllItems = new ArrayList<>();

        while (resultSet.next()){
            ItemEntity itemEntity = new ItemEntity(
                    resultSet.getString("code"),
                    resultSet.getString("description"),
                    resultSet.getBigDecimal("unitPrice"),
                    resultSet.getInt("qtyOnHand")
            );
            getAllItems.add(itemEntity);
        }
        return getAllItems;
    }

    @Override
    public boolean save(ItemEntity itemEntity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                itemEntity.getCode(),
                itemEntity.getDescription(),
                itemEntity.getUnitPrice(),
                itemEntity.getQtyOnHand()
        );
    }

    @Override
    public boolean update(ItemEntity itemEntity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                itemEntity.getDescription(),
                itemEntity.getUnitPrice(),
                itemEntity.getQtyOnHand(),
                itemEntity.getCode()
        );
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Item WHERE code=?",
                code
        );
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {

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
    public ItemEntity search(String newItemCode) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Item WHERE code=?", newItemCode);
        resultSet.next();
        return new ItemEntity(
                newItemCode + "",
                resultSet.getString("description"),
                resultSet.getBigDecimal("unitPrice"),
                resultSet.getInt("qtyOnHand")
        );
    }
}
