package lk.ijse.layeredarchitecture.bo.custom.impl;

import lk.ijse.layeredarchitecture.bo.custom.ItemsBO;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.dao.custom.ItemsDAO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.entity.ItemEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsBOImpl implements ItemsBO {

    ItemsDAO itemsDAO = (ItemsDAO) DAOFactory.getDaoFactory().getDAOObject(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {

        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<ItemEntity> itemEntities = itemsDAO.getAll();

        for (ItemEntity itemEntity : itemEntities) {
            itemDTOS.add(new ItemDTO(
                    itemEntity.getCode(),
                    itemEntity.getDescription(),
                    itemEntity.getUnitPrice(),
                    itemEntity.getQtyOnHand()
            ));
        }
        return itemDTOS;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemsDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemsDAO.save(new ItemEntity(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemsDAO.update(new ItemEntity(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand()
        ));
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
