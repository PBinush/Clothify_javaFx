package edu.icet.controller.common;

import edu.icet.controller.cards.OrderCardController;
import edu.icet.dto.Order;
import edu.icet.dto.OrderDetail;
import edu.icet.dto.cartTM;
import edu.icet.dto.Product;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.CustomerService;
import edu.icet.service.custom.OrderDetailsService;
import edu.icet.service.custom.OrderService;
import edu.icet.service.custom.ProductService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.control.TextField.*;

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
    List<cartTM> cartDetailsList = new ArrayList<>();
        public void addOrderDetails(String orderId,Integer qty){
        System.out.println(orderId+" - "+qty);
        Product productById = productService.getProductById(orderId);
        Double price = qty*productById.getPrice();

            cartDetailsList.add(
                new cartTM(
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
        ObservableList<cartTM> observableList = FXCollections.observableArrayList();
        observableList.addAll(cartDetailsList);
        tblOrderDetails.setItems(observableList);
    }

    public void dateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        lblDate.setText(simpleDateFormat.format(date));
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
        String orderId = orderService.genarateId();
        lblOrderId.setText(orderId);

        Order order = new Order(
                orderId,
                lblDate.getText(),
                findCustomer(),
                "E001",
                "NO"
        );

        List<OrderDetail> orderDetailsList = new ArrayList<>();
        cartDetailsList.forEach(cartTM -> {
            String productId = productService.getProductIdByName(cartTM.getProductName());
            orderDetailsList.add(
                    new OrderDetail(
                        cartTM.getOrderId(),
                        productId,
                        cartTM.getQtyOnHand(),
                        cartTM.getPrice()
                    )
            );
        });

        if (orderService.saveOrder(order, orderDetailsList)) {
            new Alert(Alert.AlertType.INFORMATION,"order placed successfully").show();
            orderService.genarateId();
        } else {
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
        // Load products initially
        loadProducts();

        // Auto-search feature
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(newValue);
        });

        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateAndTime();
        lblOrderId.setText(orderService.genarateId());


        ObservableList<String> possibleSuggestions = FXCollections.observableArrayList();
        customerService.getAllCustomers().forEach(customer -> {
            possibleSuggestions.add(customer.getPhoneNumber());
        });
        TextFields.bindAutoCompletion(txtNumber,possibleSuggestions);

        txtNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (possibleSuggestions.contains(newValue)) {
                customerService.getAllCustomers().forEach(customer -> {
                    if (customer.getPhoneNumber().equals(newValue)){
                        txtName.setText(customer.getName());
                    }
                });
            } else {
                txtName.clear(); // Clear if no match
            }
        });


        loadProducts();
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateAndTime();
        lblOrderId.setText(orderService.genarateId());

        colDeletebtn.setCellFactory(param -> new TableCell<cartTM, Void>() {
            private final Button deleteButton = new Button("<>");

            {
                deleteButton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setOnAction(event -> {
                    cartTM selectedItem = getTableView().getItems().get(getIndex());
                    removeOrderDetail(selectedItem);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
             });

        dateAndTime();
        lblOrderId.setText(orderService.genarateId());

    }

    private void removeOrderDetail(cartTM cartItem) {
        cartDetailsList.remove(cartItem);

        // Update the subtotal and total
        subTotal -= cartItem.getPrice();
        lblSubtotal.setText(subTotal.toString());
        lblTotal.setText(subTotal.toString());

        loadOrderDetailsTable();
    }

    private void searchProducts(String searchText) {
        searchText = searchText.trim().toLowerCase();

        // Clear the grid before adding filtered results
        placeOrderCardContainer.getChildren().clear();

        int column = 0;
        int row = 1;

        try {
            for (Product product : productsList) {
                if (product.getName().toLowerCase().contains(searchText)) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
