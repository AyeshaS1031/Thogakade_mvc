package controller.orderDetailController;

import javafx.collections.ObservableList;
import model.OrderDetails;

public interface OrderDetailManagementService {
    void addOrderDetails(OrderDetails orderD);

    void UpdateOrderDetails(OrderDetails orderD);

    void deleteOrderDetail(String text);

    ObservableList<OrderDetails> getAllOrderDetails();
}
