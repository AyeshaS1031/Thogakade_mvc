package controller.orderController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagementController implements OrderManagementService {
    ObservableList <Order> orderList = FXCollections.observableArrayList();
    @Override
    public void addOrderDetails(Order order) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (OrderID, OrderDate, CustID) VALUES(?,?,?);");
            preparedStatement.setObject(1,order.getOrderId());
            preparedStatement.setObject(2,order.getDate());
            preparedStatement.setObject(3,order.getCustomerId());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList getAllOrders() {
        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orderList.add(new Order(
                        resultSet.getString("OrderID"),
                        resultSet.getString("OrderDate"),
                        resultSet.getString("CustID")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    @Override
    public void deleteOrder(String OrderID) {
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
    public void updateOrder(Order order) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET OrderID = ?,  OrderDate = ?, CustID = ? WHERE OrderID = ?;");
            preparedStatement.setObject(1,order.getOrderId());
            preparedStatement.setObject(2,order.getDate());
            preparedStatement.setObject(3,order.getCustomerId());
            preparedStatement.setObject(4,order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
