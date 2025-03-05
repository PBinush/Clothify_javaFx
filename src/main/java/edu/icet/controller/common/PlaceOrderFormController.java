package edu.icet.controller.common;

import edu.icet.controller.cards.OrderCardController;
import edu.icet.dto.Order;
import edu.icet.dto.OrderDetails;
import edu.icet.dto.Product;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.CustomerService;
import edu.icet.service.custom.OrderDetailsService;
import edu.icet.service.custom.OrderService;
import edu.icet.service.custom.ProductService;
import edu.icet.util.ServiceType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    public GridPane placeOrderCardContainer;

    @FXML
    public TableView tblOrderDetails;

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

    private List<Product> productsList;
    final ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);
    final OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceTpe(ServiceType.ORDER_DETAILS);
    final OrderService orderService = ServiceFactory.getInstance().getServiceTpe(ServiceType.ORDER);
    final CustomerService customerService = ServiceFactory.getInstance().getServiceTpe(ServiceType.CUSTOMERS);

    public void loadProducts() {
        productsList = productService.getAllProducts();

        int column = 0;
        int row = 1;

        try {
            for (Product product : productsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/cards/order_card.fxml"));
                VBox cardBox = fxmlLoader.load();
                OrderCardController orderCardController = fxmlLoader.getController();
                orderCardController.setData(product);

                orderCardController.setOrderCardListener((productId, quantity) -> {
                    System.out.println("Added to cart: " + productId + " | Quantity: " + quantity);
                    addOrderDetails(productId, quantity);
                });

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                placeOrderCardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Double subTotal=0.0;
    List<OrderDetails> orderDetailsList = new ArrayList<>();
    public void addOrderDetails(String orderId,Integer qty){
        System.out.println(orderId+" - "+qty);
        Product productById = productService.getProductById(orderId);
        Double price = qty*productById.getPrice();
//        orderDetailsService.saveOrderDetail(
//                new OrderDetails(
//                        lblOrderId.getText(),
//                        productById.getName(),
//                        qty,
//                        price
//                )
//        );
        orderDetailsList.add(
                new OrderDetails(
                        lblOrderId.getText(),
                        productById.getName(),
                        qty,
                        price
                )
        );
        subTotal +=  price;
        lblSubtotal.setText(subTotal.toString());
        lblTotal.setText(subTotal.toString());
        loadOrderDetailsTable();
    }

    public void loadOrderDetailsTable(){
        ObservableList<OrderDetails> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderDetailsList);
        tblOrderDetails.setItems(observableList);
    }

    public void dateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        lblDate.setText(simpleDateFormat.format(date));

//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, e->{
//                    LocalTime now = LocalTime.now();
//                    lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
//                }),
//                new KeyFrame(Duration.seconds(1))
//        );
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }

    String custId;
    public String findCustomer(){
        customerService.getAllCustomers().forEach(customer->{
            try{
                if (customer.getName().equals(txtName.getText()) && customer.getPhoneNumber().equals(txtNumber.getText())){
                     custId = customer.getId();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"customer is not registered").show();
            }
        });
        return custId;
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        boolean b = orderService.saveOrder(
                new Order(
                        null,
                        lblDate.getText(),
                        findCustomer(),
                        "user",
                        orderDetailsList
                )
        );
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"order place successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProducts();
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateAndTime();
//        lblOrderId.setText(orderService.genarateId());
    }
}
