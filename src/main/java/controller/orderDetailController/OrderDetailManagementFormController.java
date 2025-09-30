package controller.orderDetailController;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.OrderDetails;
import stage.createStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailManagementFormController implements Initializable {
    ObservableList <OrderDetails> orderDetails = FXCollections.observableArrayList();

    @FXML
    private ComboBox<?> cmboDiscount;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<OrderDetails> tblItems;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtQty;

    Stage dashboard = createStage.createDashboardStage();

    OrderDetailManagementService orderDetailManagementService = new OrderDetailManagementController();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        OrderDetails orderD = new OrderDetails(
                txtOrderId.getText(),
                txtItemCode.getText(),
                txtQty.getText(),
                String.valueOf(cmboDiscount.getValue())
        );
        orderDetailManagementService.addOrderDetails(orderD);
        loadAllOrderDetails();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtOrderId.setText(null);
        txtItemCode.setText(null);
        txtQty.setText(null);
        cmboDiscount.setValue(null);
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
        orderDetailManagementService.deleteOrderDetail(txtOrderId.getText());
        loadAllOrderDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        OrderDetails orderD = new OrderDetails(
                txtOrderId.getText(),
                txtItemCode.getText(),
                txtQty.getText(),
                String.valueOf(cmboDiscount.getValue())
        );
        orderDetailManagementService.UpdateOrderDetails(orderD);
        loadAllOrderDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList discount = FXCollections.observableArrayList();
        discount.add("0");
        discount.add("5");
        discount.add("10");
        cmboDiscount.setItems(discount);

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        loadAllOrderDetails();
    }

    private void loadAllOrderDetails() {
        orderDetails.clear();
        orderDetails= orderDetailManagementService.getAllOrderDetails();
        tblItems.setItems(orderDetails);
    }
}
