package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.ItemsDAO;
import com.example.layeredarchitecture.dao.custom.impl.ItemsDAOImpl;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsBOImpl implements ItemsBO{

    ItemsDAO itemsDAO = new ItemsDAOImpl();

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemsDAO.getAll();
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemsDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemsDAO.save(itemDTO);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemsDAO.update(itemDTO);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemsDAO.exist(code);
    }

    @Override
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return itemsDAO.generateNewId();
    }
}
