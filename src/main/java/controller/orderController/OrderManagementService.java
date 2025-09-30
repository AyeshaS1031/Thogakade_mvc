package controller.orderController;

import javafx.collections.ObservableList;
import model.Order;

public interface OrderManagementService {
    void addOrderDetails(Order order);

    ObservableList getAllOrders();

    void deleteOrder(String text);

    void updateOrder(Order order);
}
