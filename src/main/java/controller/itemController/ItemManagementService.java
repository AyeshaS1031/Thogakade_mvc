package controller.itemController;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemManagementService {

    void addItemsDetails(Item item);

    void deleteItemDetail(String text);

    void updateCustomerDetails(Item item);

    ObservableList getAllItemDetails();
}
