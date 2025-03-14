package edu.icet.controller.cards;

import edu.icet.dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderCardController implements Initializable {

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label lblProductName;

    @FXML
    private Label lblProductPrice;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Spinner spnQtyProduct;

    @FXML
    private VBox vbProduct;

    private String cardId;
    private OrderCardListener orderCardListener; // Callback interface

    public void setData(Product product){
        cardId = product.getId();
        lblProductName.setText(product.getName());
        lblQtyOnHand.setText(product.getQty().toString());
        lblProductPrice.setText(product.getPrice().toString());

        spnQtyProduct.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getQty(), 1) // Set max to stock qty
        );

        if (product.getImgPath() != null) {
            String cleanedPath = product.getImgPath().replace("file:/", "");
            cleanedPath = cleanedPath.replace("%20", " ");

            File file = new File(cleanedPath);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imgProduct.setImage(image);

                imgProduct.setVisible(true);
            } else {
                System.out.println("Image not found at: " + cleanedPath);
                imgProduct.setImage(new Image("file:/D:/Git%20Project/ClothifyStore/src/main/resources/img/cartdetails/cart_product.png"));
            }
        } else {
            imgProduct.setImage(new Image("file:/D:/Git%20Project/ClothifyStore/src/main/resources/img/cartdetails/cart_product.png"));
        }
    }

    @FXML
    void btnAddtocartOnAction(ActionEvent event) {
        int selectedQty = (int) spnQtyProduct.getValue(); // Get selected quantity
        if (orderCardListener != null) {
            orderCardListener.onAddToCart(cardId, selectedQty); // Pass data to listener
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spnQtyProduct.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    // Setter for listener
    public void setOrderCardListener(OrderCardListener listener) {
        this.orderCardListener = listener;
    }
}
