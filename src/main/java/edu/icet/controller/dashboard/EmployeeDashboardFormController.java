package edu.icet.controller.dashboard;

import edu.icet.dto.Order;
import edu.icet.service.ServiceFactory;
import edu.icet.util.ServiceType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EmployeeDashboardFormController {

    public AnchorPane ancLogin;
    public AnchorPane anc2;
    public LineChart<String, Number> progressChart;
    @FXML
    private ImageView imgAvotor1;

    @FXML
    private ImageView imgAvotor2;

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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/dashboard/employee_dashboard_form.fxml"));
            ancLogin.getChildren().setAll(pane);
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

    public void btnProfileOnAction1(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/common/userProfile.fxml"))));
        stage.show();
    }

    public void btnProfileOnAction2(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/common/userProfile.fxml"))));
        stage.show();
    }
}
