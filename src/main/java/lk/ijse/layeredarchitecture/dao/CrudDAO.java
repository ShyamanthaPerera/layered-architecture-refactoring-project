package lk.ijse.layeredarchitecture.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/*Facade design pattern - Hide the complexibility*/

public interface CrudDAO<T> extends SuperDAO {

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T customerDTO) throws SQLException, ClassNotFoundException;

    boolean update(T customerDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    T search(String newValue) throws SQLException, ClassNotFoundException;
}
