package controller.orderDetailController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailManagementController implements OrderDetailManagementService {
    ObservableList <OrderDetails> orderDtl= FXCollections.observableArrayList();
    @Override
    public void addOrderDetails(OrderDetails orderD) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderdetail (OrderID, ItemCode, OrderQTY, Discount) VALUES(?,?,?,?);");
            preparedStatement.setObject(1,orderD.getOrderId());
            preparedStatement.setObject(2,orderD.getItemCode());
            preparedStatement.setObject(3,orderD.getOrderQty());
            preparedStatement.setObject(4,orderD.getDiscount());

            preparedStatement.executeUpdate();

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateOrderDetails(OrderDetails orderD) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orderdetail SET OrderID = ?,  ItemCOde = ?, OrderQTY = , Discount = ? WHERE OrderID = ?;");
            preparedStatement.setObject(1,orderD.getOrderId());
            preparedStatement.setObject(2,orderD.getItemCode());
            preparedStatement.setObject(3,orderD.getOrderQty());
            preparedStatement.setObject(4,orderD.getDiscount());
            preparedStatement.setObject(5,orderD.getOrderId());

            preparedStatement.executeUpdate();

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteOrderDetail(String OrderID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM orders WHERE OrderID= ?;");
            preparedStatement.setObject(1,OrderID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<OrderDetails> getAllOrderDetails() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orderdetail;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orderDtl.add(new OrderDetails(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getString("OrderQTY"),
                        resultSet.getString("Discount")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDtl;
    }
}
