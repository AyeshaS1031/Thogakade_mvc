package edu.icet.controller;

import edu.icet.stage.createStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    Stage customerManagement = createStage.createCustomerStage();
    Stage itemManagement = createStage.createItemStage();
    Stage orderDtlManagement = createStage.createOrderDetailStage();
    Stage orderManagement= createStage.createOrderStage();

    @FXML
    void btnManageCustomers(javafx.event.ActionEvent event) {
        try {
            customerManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customerManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        customerManagement.show();

    }

    @FXML
    void btnManageItems(javafx.event.ActionEvent event) {
        try {
            itemManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/itemManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        itemManagement.show();

    }

    @FXML
    void btnManageOrderDetails(javafx.event.ActionEvent event) {
        try {
            orderDtlManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/orderDtlManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderDtlManagement.show();

    }

    @FXML
    void btnManageOrders(ActionEvent event) {
        try {
            orderManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/orderManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderManagement.show();
    }
}
