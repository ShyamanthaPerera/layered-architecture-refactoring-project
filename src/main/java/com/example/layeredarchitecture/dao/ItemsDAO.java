package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemsDAO {

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean itemSaveOnAction(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean itemUpdateOnAction(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean itemDeleteOnAction(String code) throws SQLException, ClassNotFoundException;

    public boolean existItem(String code) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
}
