package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean customerSaveOnAction(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean customerUpdateOnAction(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean customerDeleteOnAction(String id) throws SQLException, ClassNotFoundException;

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;
}
