package edu.icet.controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class OrdersFormController {

    @FXML
    private AnchorPane anc2;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDateAndTime;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colItems;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private ImageView imgAvotor;

    @FXML
    private Label lblID;

    @FXML
    private Label lblID1;

    @FXML
    private Label lblID11;

    @FXML
    private Label lblID12;

    @FXML
    private Label lblID13;

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
