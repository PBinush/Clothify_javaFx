package edu.icet.controller.cards.product_categories;

import edu.icet.dto.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
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
//        if (product.getImgPath()!=null) {
//            String correctedPath = imgProduct.getImage().getUrl().replace(" ", "%20");
//
////            File file = new File(product.getImgPath());
////            Image image = new Image(file.toURI().toString());
//            imgProduct.setImage(new Image(correctedPath));
//            imgProduct.setVisible(true);
//        }else{
//            imgProduct.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//cartdetails//cart_product.png"));
//        }
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

    public void setOnCardClick(Consumer<String> listener) {
        this.onCardClick = listener;
        vbProduct.setOnMouseClicked(event -> {
            if (onCardClick != null) {
                onCardClick.accept(cardId);
            }
        });
    }

}
