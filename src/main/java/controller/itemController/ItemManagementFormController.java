package controller.itemController;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import stage.createStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ItemManagementFormController implements Initializable {
    ObservableList itemList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<?> tblItems;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPacKSize;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQuantity;

    ItemManagementService itemManagementService =  new ItemManagementController();
    Stage dashboard = createStage.createDashboardStage();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPacKSize.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQuantity.getText())
        );
        itemManagementService.addItemsDetails(item);
        loadItemDetails();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtItemCode.setText(null);
        txtDescription.setText(null);
        txtPacKSize.setText(null);
        txtPrice.setText(null);
        txtQuantity.setText(null);
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
        itemManagementService.deleteItemDetail(txtItemCode.getText());
        loadItemDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPacKSize.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQuantity.getText())
        );
        itemManagementService.updateCustomerDetails(item);
        loadItemDetails();

    }

    private void loadItemDetails() {
        itemList.clear();
        itemList=itemManagementService.getAllItemDetails();
        tblItems.setItems(itemList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadItemDetails();
    }

}
