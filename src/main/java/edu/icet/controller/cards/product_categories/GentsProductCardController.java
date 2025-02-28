package edu.icet.controller.cards.product_categories;

import edu.icet.dto.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class GentsProductCardController {

    @FXML
    public Label lblProductPrice;

    @FXML
    public VBox vbProduct;

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label lblProductName;

    @FXML
    private Label lblQtyOnHand;
    private String cardId;
    private Consumer<String> onCardClick;


    public void setData(Product product){
        cardId=product.getId();
        lblProductName.setText(product.getName());
        lblQtyOnHand.setText(product.getQty().toString());
        lblProductPrice.setText(product.getPrice().toString());
    }

    public void setOnCardClick(Consumer<String> listener) {
        this.onCardClick = listener;
        vbProduct.setOnMouseClicked(event -> {
            if (onCardClick != null) {
                onCardClick.accept(cardId);
            }
        });
    }

//    public void  setImage(Image image){
//        if (image != null) {
//            Image image = new Image(product.getImagePath());
//            imgProduct.setImage(image);
//        }
//    }
}
