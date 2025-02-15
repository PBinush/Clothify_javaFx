package edu.icet.controller.common;

import edu.icet.dto.Products;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.ProductService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

    @FXML
    public TextField txtQty;

    @FXML
    public ComboBox cmbCategory;

    @FXML
    public ComboBox cmbSize;

    @FXML
    public TextField txtPrice;

    @FXML
    private AnchorPane anc2;

    @FXML
    private AnchorPane ancProductCategory;

    @FXML
    private ImageView imgAvotor;

    @FXML
    private Label lblID;


    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);
        Products product = new Products(
                txtName.getText(),
                cmbSize.getValue().toString(),
                Integer.parseInt(txtQty.getText()),
                cmbCategory.getValue().toString(),
                Double.parseDouble(txtPrice.getText())
        );
        if (productService.saveProduct(product)){
            new Alert(Alert.AlertType.INFORMATION,"Product Added Successfully").show();
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
        ObservableList<String> sizeList = FXCollections.observableArrayList();
        sizeList.add("M");
        sizeList.add("L");
        sizeList.add("S");
        sizeList.add("SM");
        sizeList.add("XL");
        cmbSize.setItems(sizeList);

        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("Gents");
        categoryList.add("Kids");
        categoryList.add("Ladies");
        cmbCategory.setItems(categoryList);
    }
}
