package controller.customerController;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerManagementService {
    void addCustomerDetails(Customer customer);

    void deleteCustomerdetails(String text);

    void updateCustomerDetails(Customer customerDetails);

    ObservableList<Customer> getAllCustomerDetails();
}
