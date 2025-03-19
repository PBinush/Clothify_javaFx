package edu.icet.controller.dashboard;

import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.CustomerService;
import edu.icet.service.custom.EmployeeService;
import edu.icet.service.custom.OrderService;
import edu.icet.service.custom.ProductService;
import edu.icet.util.ServiceType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDashboardFormController implements Initializable {

    @FXML
    public ImageView imgTrendingProduct;

    @FXML
    public ImageView imgBest;

    @FXML
    public ImageView imgProfile2;

    @FXML
    public ImageView imgProfile1;

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

    CustomerService customerService = ServiceFactory.getInstance().getServiceTpe(ServiceType.CUSTOMERS);
    public void addCustomerCount(){
        ArrayList<String> arrayList = new ArrayList<>();
        customerService.getAllCustomers().forEach(customer -> {
            arrayList.add(customer.getId());
        });
        long count = arrayList.stream().count();
        String count1 = Long.toString(count);
        lblCustomersCount.setText(count1);
    }

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceTpe(ServiceType.EMPLOYEE);
    public void addEmployeeCount(){
        ArrayList<String> arrayList = new ArrayList<>();
        employeeService.getAllEmployee().forEach(employee -> {
            arrayList.add(employee.getId());
        });
        long count = arrayList.stream().count();
        String count1 = Long.toString(count);
        lblEmpCount.setText(count1);
    }

    ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);
    public void addProductCount(){
        ArrayList<String> arrayList = new ArrayList<>();
        productService.getAllProducts().forEach(product -> {
            arrayList.add(product.getId());
        });
        long count = arrayList.stream().count();
        String count1 = Long.toString(count);
        lblStocksCount.setText(count1);
    }

    OrderService orderService = ServiceFactory.getInstance().getServiceTpe(ServiceType.ORDER);
    public void addTodayBalance(){
        String s = orderService.todayOrdersBalance();
        lblSalesPrice.setText(s);
    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductCount();
        addEmployeeCount();
        addCustomerCount();
    }
}
