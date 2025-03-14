package edu.icet.controller.common;

import edu.icet.controller.cards.E_S_C_CardController;
import edu.icet.dto.Customer;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.CustomerService;
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

public class CustomerFormController implements Initializable {
    @FXML
    public GridPane customerCardContainer;

    @FXML
    public ComboBox cmbTitle;

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

    public void search(){
        txtSearch.textProperty().addListener((observableValue, s, t1) -> {
            System.out.println(t1);
        });
    }

    List<Customer> customerList;
    final CustomerService customerService = ServiceFactory.getInstance().getServiceTpe(ServiceType.CUSTOMERS);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        CustomerService serviceTpe = ServiceFactory.getInstance().getServiceTpe(ServiceType.CUSTOMERS);
        Customer customer = new Customer(
                null,
                cmbTitle.getValue().toString(),
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
        loadCustomer();
        clearCartDetails();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(customerService.deleteCustomer(lblID.getText())){
            new Alert(Alert.AlertType.ERROR,"Delete Successfully : "+lblID.getText()).show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadCustomer();
        clearCartDetails();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CustomerService serviceTpe = ServiceFactory.getInstance().getServiceTpe(ServiceType.CUSTOMERS);
        Customer customer = new Customer(
                lblID.getText(),
                cmbTitle.getValue().toString(),
                txtName.getText(),
                txtAddress.getText(),
                txtNumber.getText(),
                txtEmail.getText()
        );
        if (customerService.updateCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Update Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadCustomer();
        clearCartDetails();
    }

    public void loadCustomer(){
        customerList = customerService.getAllCustomers();

        int column = 0;
        int row = 1;

        try {
            for (Customer customer : customerList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/cards/e_s_c_card.fxml"));
                VBox cardBox = fxmlLoader.load();
                E_S_C_CardController e_s_c_cardController = fxmlLoader.getController();
                e_s_c_cardController.setData(customer);
                e_s_c_cardController.setOnCardClick(this::handleCustomerCardClick);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                customerCardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCustomerCardClick(String productId) {
        System.out.println("Clicked Customer ID: " + productId);
        loadDetails(productId);
    }

    public void clearCartDetails(){
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtNumber.clear();
        cmbTitle.setValue(null);
    }

    public void loadDetails(String id){
        Customer customerById = customerService.getCustomerById(id);
        txtName.setText(customerById.getName());
        txtAddress.setText(customerById.getAddress());
        txtEmail.setText(customerById.getEmail());
        txtNumber.setText(customerById.getPhoneNumber());
        lblID.setText(customerById.getId());
        cmbTitle.setValue(customerById.getTitle());
        if (customerById.getTitle().equals("Mr")) {
            imgAvotor.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//Employee-male.png"));
        }else {
            imgAvotor.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//employee-female.png"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("Mr");
        titleList.add("Miss");
        titleList.add("Mrs");
        cmbTitle.setItems(titleList);

        lblID.setText(customerService.genarateId());
        loadCustomer();
    }
}
