package edu.icet.controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlaceOrderFormController {

    @FXML
    private ImageView addCustomer;

    @FXML
    private AnchorPane anc2;

    @FXML
    private TableColumn colDeletebtn;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colQty;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDiscount;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTotal;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    public void btnAddCustomer(MouseEvent mouseEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/customer_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
