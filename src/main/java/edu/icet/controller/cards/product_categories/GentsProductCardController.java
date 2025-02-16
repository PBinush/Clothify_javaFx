package edu.icet.controller.cards.product_categories;

import edu.icet.dto.Products;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.ProductService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class GentsProductCardController {

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label lblProductName;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private TextField txtQty;

    public void setData(Products product){
        lblProductName.setText(product.getName());
        lblQtyOnHand.setText(product.getQty().toString());
    }
}
