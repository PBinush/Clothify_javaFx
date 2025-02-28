package edu.icet.controller.common;

import edu.icet.controller.cards.product_categories.GentsProductCardController;
import edu.icet.dto.Product;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.ProductService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

    @FXML
    private AnchorPane ancProductCategory;

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
    private GridPane productCardContainer;

    @FXML
    private ImageView imgAvotor;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    private List<Product> productsList;

    final ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);

    public void loadGentsProducts() {
        productsList = productService.getAllGentsProduct();

        int column = 0;
        int row = 1;

        try {
            for (Product product : productsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/cards/product_categories/gents_product_card.fxml"));
                VBox cardBox = fxmlLoader.load();
                GentsProductCardController gentsProductCardController = fxmlLoader.getController();
                gentsProductCardController.setData(product);
                gentsProductCardController.setOnCardClick(this::handleProductCardClick);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                productCardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void loadLadiesProducts() {
        productsList = productService.getAllLadiesProduct();

        int column = 0;
        int row = 1;

        try {
            for (Product product : productsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/cards/product_categories/gents_product_card.fxml"));
                VBox cardBox = fxmlLoader.load();
                GentsProductCardController gentsProductCardController = fxmlLoader.getController();
                gentsProductCardController.setData(product);
                gentsProductCardController.setOnCardClick(this::handleProductCardClick);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                productCardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleProductCardClick(String productId) {
        System.out.println("Clicked Product ID: " + productId);
        loadDetails(productId);
    }

    public void loadDetails(String id){
        Product productById = productService.getProductById(id);
        txtName.setText(productById.getName());
        txtPrice.setText(productById.getPrice().toString());
        txtQty.setText(productById.getQty().toString());
        cmbCategory.setValue(productById.getCategory());
        cmbSize.setValue(productById.getSize());
        lblID.setText(productById.getId());
    }

    public void clearCartDetails(){
        txtName.clear();
        txtQty.clear();
        txtPrice.clear();
        cmbSize.setValue(null);
        cmbCategory.setValue(null);
    }

    //in-progress
    public void imageGenarate(){
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Product product = new Product(
                null,
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
        lblID.setText(productService.genarateId());
        loadGentsProducts();
        clearCartDetails();
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

    public void btnLadiesOnAction(ActionEvent actionEvent) {
        loadGentsProducts();
    }

    public void btnGentsOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/product_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnKidsOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/product_kids_category_form.fxml"));
            ancProductCategory.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        lblID.setText(productService.genarateId());
        loadGentsProducts();
    }
}
