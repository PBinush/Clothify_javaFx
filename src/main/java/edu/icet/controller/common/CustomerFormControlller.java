package edu.icet.controller.common;

import edu.icet.dto.Customer;
import edu.icet.service.ServiceFactory;
import edu.icet.service.SuperService;
import edu.icet.service.custom.CustomerService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CustomerFormControlller {
    @FXML
    private AnchorPane anc2;

    @FXML
    private ImageView imgAvotor;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        CustomerService serviceTpe = ServiceFactory.getInstance().getServiceTpe(ServiceType.CUSTOMERS);
        Customer customer = new Customer(
                txtName.getText(),
                txtAddress.getText(),
                txtNumber.getText(),
                txtEmail.getText()
        );
        if (serviceTpe.saveCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"customer added successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
