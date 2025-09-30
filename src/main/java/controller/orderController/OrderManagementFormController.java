package controller.orderController;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Order;
import stage.createStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {
    ObservableList <Order>orderList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<?> cmboItemList;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colItems;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderStatus;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private DatePicker dateOrderDate;

    @FXML
    private RadioButton rdoCancelled;

    @FXML
    private RadioButton rdoDispatched;

    @FXML
    private RadioButton rdoProcessing;

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private TableView<Order> tblOrderList;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTotalPrice;

    OrderManagementService orderManagementService = new OrderManagementController();
    Stage dashboard = createStage.createDashboardStage();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Order order = new Order(
                txtOrderId.getText(),
                String.valueOf(dateOrderDate.getValue()),
                txtCustomerId.getText()
        );
        orderManagementService.addOrderDetails(order);
         loadOrderDetails();


    }

    private void loadOrderDetails() {
        orderList.clear();
        orderList = orderManagementService.getAllOrders();
        tblOrderList.setItems(orderList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtOrderId.setText(null);
        txtCustomerId.setText(null);
        dateOrderDate.setValue(null);

    }

    @FXML
    void btnDashboard(ActionEvent event) {
        try {
            dashboard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dashboard.show();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        orderManagementService.deleteOrder(txtOrderId.getText());
        loadOrderDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Order order = new Order(
                txtOrderId.getText(),
                String.valueOf(dateOrderDate.getValue()),
                txtCustomerId.getText()
        );
        orderManagementService.updateOrder(order);
        loadOrderDetails();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderDetails();
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }
}
