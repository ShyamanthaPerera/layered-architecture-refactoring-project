package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<CustomerDTO> getAllCustomers = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address")
            );
            getAllCustomers.add(customerDTO);
        }
        return getAllCustomers;
    }

    @Override
    public boolean customerSaveOnAction(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress()
        );
    }

    @Override
    public boolean customerUpdateOnAction(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getId()
        );
    }

    @Override
    public boolean customerDeleteOnAction(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",
                id
        );
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SQLUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");

        if (resultSet.next()) {
            String id = resultSet.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer WHERE id=?");
        resultSet.next();
        return new CustomerDTO(newValue + "", resultSet.getString("name"), resultSet.getString("address"));
    }
}
