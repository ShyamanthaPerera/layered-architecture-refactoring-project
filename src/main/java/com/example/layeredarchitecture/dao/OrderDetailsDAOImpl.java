package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDetailDTO;
import java.sql.SQLException;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean saveOrderDetails(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                orderDetailDTO.getOid(),
                orderDetailDTO.getItemCode(),
                orderDetailDTO.getUnitPrice(),
                orderDetailDTO.getQty()
                );
    }
}
