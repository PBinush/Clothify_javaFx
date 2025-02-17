package edu.icet.controller.common;

import edu.icet.dto.Supplier;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    public ComboBox cmbItems;

    @FXML
    public TextField txtCompany;

    @FXML
    public ComboBox cmbTitle;

    @FXML
    private AnchorPane anc2;

    @FXML
    private ImageView imgAvotor;

    @FXML
    private Label lblID;

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
        SupplierService supplierService = ServiceFactory.getInstance().getServiceTpe(ServiceType.SUPPLIER);
        Supplier supplier = new Supplier(
                null,
                cmbTitle.getValue().toString(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );
        if (supplierService.SaveSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added Successful").show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("Mr");
        titleList.add("Miss");
        titleList.add("Mrs");
        cmbTitle.setItems(titleList);
    }
}
