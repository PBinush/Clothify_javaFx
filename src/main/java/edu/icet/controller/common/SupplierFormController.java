package edu.icet.controller.common;

import edu.icet.controller.cards.E_S_C_CardController;
import edu.icet.dto.Customer;
import edu.icet.dto.Supplier;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.ProductService;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    public ComboBox cmbItems;

    @FXML
    public TextField txtCompany;

    @FXML
    public ComboBox cmbTitle;

    @FXML
    public GridPane supplierCardContainer;

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

    List<Supplier> supplierList;
    final SupplierService supplierService = ServiceFactory.getInstance().getServiceTpe(ServiceType.SUPPLIER);
    final ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);

    public void loadSupplier(){
        supplierList = supplierService.getAllSuppliers();

        int column = 0;
        int row = 1;

        try {
            for (Supplier supplier : supplierList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/cards/e_s_c_card.fxml"));
                VBox cardBox = fxmlLoader.load();
                E_S_C_CardController e_s_c_cardController = fxmlLoader.getController();
                e_s_c_cardController.setData1(supplier);
                e_s_c_cardController.setOnCardClick(this::handleSupplierCardClick);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                supplierCardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSupplierCardClick(String productId) {
        System.out.println("Clicked Customer ID: " + productId);
        loadDetails(productId);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                null,
                cmbTitle.getValue().toString(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                cmbItems.getValue().toString()
        );
        if (supplierService.saveSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadSupplier();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        supplierService.deleteSupplier(lblID.getText());
        System.out.println(lblID.getText());
        loadSupplier();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                null,
                cmbTitle.getValue().toString(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                cmbItems.getValue().toString()
        );
        if (supplierService.updateSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Update Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadSupplier();
    }

    public void clearCartDetails(){
        txtName.clear();
        cmbTitle.setValue(null);
        cmbItems.setValue(null);
        txtCompany.clear();
        txtEmail.clear();
    }

    public void loadDetails(String id){
        Supplier supplierById = supplierService.getSupplierById(id);
        cmbTitle.setValue(supplierById.getTitle());
        txtName.setText(supplierById.getName());
        txtCompany.setText(supplierById.getCompany());
        txtEmail.setText(supplierById.getEmail());
//        cmbItems.setValue(supplierById.);
        lblID.setText(supplierById.getId());
        if (supplierById.getTitle().equals("Mr")) {
            imgAvotor.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//customer-male.png"));
        }else {
            imgAvotor.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//customer-female.png"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("Mr");
        titleList.add("Miss");
        titleList.add("Mrs");
        cmbTitle.setItems(titleList);

        lblID.setText(supplierService.genarateId());
        loadSupplier();

        ObservableList<String> names = FXCollections.observableArrayList();
        names.addAll(productService.getAllProductIds());
        cmbItems.setItems(names);
    }
}
