package edu.icet.controller.dashboard;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class AdminDashboardFormController {

    @FXML
    public ImageView imgTrendingProduct;

    @FXML
    public ImageView imgBest;

    @FXML
    private AnchorPane anc2;

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblBestName;

    @FXML
    private Label lblBestPosition;

    @FXML
    private Label lblCustomersCount;

    @FXML
    private Label lblEmpCount;

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblSalesPrice;

    @FXML
    private Label lblStocksCount;

    @FXML
    private Label lblTrendingProductName;

    @FXML
    private Label lblTrendingProductPrice;

    @FXML
    private Label lblTrendingProductQty;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/customer_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/dashboard/admin_dashboard_form.fxml"));
            ancLogin.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/admin/employee_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/orders_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/place_order_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProductOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/product_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/common/supplier_form.fxml"));
            anc2.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
