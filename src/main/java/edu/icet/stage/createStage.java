package edu.icet.stage;

import javafx.stage.Stage;

public class createStage {
    private static Stage customerManagement;
    private static Stage itemManagement ;
    private static Stage orderManagement ;
    private static Stage orderDtlManagement ;
    private static Stage dashboard;

    public static Stage createDashboardStage(){
        return dashboard == null? dashboard=new Stage():dashboard;
    }
    public static Stage createCustomerStage(){
        return customerManagement == null? customerManagement=new Stage():customerManagement;
    }
    public static Stage createItemStage(){
        return itemManagement == null? itemManagement=new Stage():itemManagement;
    }
    public static Stage createOrderStage(){
        return orderManagement == null? orderManagement=new Stage():orderManagement;
    }
    public static Stage createOrderDetailStage(){
        return orderDtlManagement == null? orderDtlManagement=new Stage():orderDtlManagement;
    }
}


