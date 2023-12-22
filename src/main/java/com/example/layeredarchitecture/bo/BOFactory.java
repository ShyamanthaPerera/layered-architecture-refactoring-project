package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemsBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PLACE_ORDER
    }

    public SuperBO getBOObjects(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemsBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
