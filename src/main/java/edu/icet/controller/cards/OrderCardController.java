package edu.icet.controller.cards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class OrderCardController {

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

    @FXML
    void btnAddtocartOnAction(ActionEvent event) {

    }

}
