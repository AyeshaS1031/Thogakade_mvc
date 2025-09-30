package controller.customerController;

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
import model.Customer;
import stage.createStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class CustomerManagementFormController implements Initializable {


    ObservableList <Customer> customerDetails = FXCollections.observableArrayList();


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostal;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private RadioButton rdoBtnMiss;

    @FXML
    private RadioButton rdoBtnMr;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostal;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;


    CustomerManagementService managementService = new CustomerManagementController();

    Customer customer;
    Stage dashboard = createStage.createDashboardStage();





    @FXML
    void btnAddOnAction(ActionEvent event) {

        customer  = new Customer(
                txtCustomerId.getText(),
                checkCustomerTitle(),
                txtName.getText(),
                String.valueOf(dateDOB.getValue()),
                txtSalary.getText(),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostal.getText()
        );

        managementService.addCustomerDetails(customer);
        loadCustomerDetails();



    }


    private String checkCustomerTitle() {
        if(rdoBtnMiss.isSelected()) return "Miss";
        if (rdoBtnMr.isSelected()) return "Mr";

        return "not selected";
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtCustomerId.setText(null);
        rdoBtnMr.setSelected(false);
        rdoBtnMiss.setSelected(false);
        txtName.setText(null);
        dateDOB.setValue(null);
        txtAddress.setText(null);
        txtCity.setText(null);
        txtProvince.setText(null);
        txtPostal.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        managementService.deleteCustomerdetails(txtCustomerId.getText());
        loadCustomerDetails();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer customerDetails = new Customer(
                txtCustomerId.getText(),
                checkCustomerTitle(),
                txtName.getText(),
                String.valueOf(dateDOB.getValue()),
                txtSalary.getText(),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostal.getText()
        );
        managementService.updateCustomerDetails(customerDetails);
        loadCustomerDetails();

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadCustomerDetails();
    }

    private void loadCustomerDetails() {
        customerDetails.clear();
        customerDetails = managementService.getAllCustomerDetails();
        tblCustomers.setItems(customerDetails);
    }

}
