package controller.customerController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;

public class CustomerManagementController implements CustomerManagementService {
    ObservableList <Customer> custDetails = FXCollections.observableArrayList();

    @Override
    public void addCustomerDetails(Customer customer) {
        Connection connection=null;

        try {
            connection = DBConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer (CustID,CustTitle,CustName,DOB,salary,CustAddress,City,Province,PostalCode) VALUES(?,?,?,?,?,?,?,?,?);");

            preparedStatement.setObject(1,customer.getId());
            preparedStatement.setObject(2,customer.getTitle());
            preparedStatement.setObject(3, customer.getName());
            preparedStatement.setObject(4, customer.getDob());
            preparedStatement.setObject(5, customer.getSalary());
            preparedStatement.setObject(6, customer.getAddress());
            preparedStatement.setObject(7,customer.getCity());
            preparedStatement.setObject(8,customer.getProvince());
            preparedStatement.setObject(9,customer.getPostalCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAllCustomerDetails() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                custDetails.add(new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getString("DOB"),
                        resultSet.getString("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return custDetails;
    }

    @Override
    public void deleteCustomerdetails(String customerId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE CustID = ?;");

            preparedStatement.setObject(1,customerId);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCustomerDetails(Customer customerDetails) {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET CustID = ? ,CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?, City = ?, Province = ?, PostalCode = ?  WHERE CustID = ?;");

            preparedStatement.setObject(1,customerDetails.getId());
            preparedStatement.setObject(2,customerDetails.getTitle());
            preparedStatement.setObject(3,customerDetails.getName());
            preparedStatement.setObject(4,customerDetails.getDob());
            preparedStatement.setObject(5,customerDetails.getSalary());
            preparedStatement.setObject(6,customerDetails.getAddress());
            preparedStatement.setObject(7,customerDetails.getCity());
            preparedStatement.setObject(8,customerDetails.getProvince());
            preparedStatement.setObject(9,customerDetails.getPostalCode());
            preparedStatement.setObject(10,customerDetails.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
