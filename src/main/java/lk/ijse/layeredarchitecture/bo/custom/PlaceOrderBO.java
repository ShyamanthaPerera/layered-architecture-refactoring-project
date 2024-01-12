package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.bo.SuperBO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;
import lk.ijse.layeredarchitecture.entity.CustomerEntity;
import lk.ijse.layeredarchitecture.entity.ItemEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    ItemEntity findItem(String code);

    CustomerEntity searchCustomer(String id) throws SQLException, ClassNotFoundException;
    
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    ItemEntity searchItem(String code) throws SQLException, ClassNotFoundException;

    String generateNewOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerEntity> getAllCustomersId() throws SQLException, ClassNotFoundException;

    ArrayList<ItemEntity> getAllItemsCode() throws SQLException, ClassNotFoundException;
}
