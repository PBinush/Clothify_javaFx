package edu.icet.controller.common;

import edu.icet.controller.cards.OrderCardController;
import edu.icet.dto.OrderDetails;
import edu.icet.dto.Product;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.OrderDetailsService;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    public GridPane placeOrderCardContainer;
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
    List<OrderDetails> orderDetailsList = new ArrayList<>();

    final ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);
    final OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceTpe(ServiceType.ORDER_DETAILS);
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
                    saveOrderDetails(productId, quantity);
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

    public void saveOrderDetails(String orderId,Integer qty){
        System.out.println(orderId+" - "+qty);
        Product productById = productService.getProductById(orderId);
        Double price = qty*productById.getPrice();

        boolean b = orderDetailsService.saveOrderDetail(
                new OrderDetails(
                        productById.getName(),
                        qty,
                        price
                )
        );
        if (b){
            new Alert(Alert.AlertType.INFORMATION,orderId+"-add to cart successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadOrderDetailsTable();
    }

    public void loadOrderDetailsTable(){
        ObservableList<OrderDetails> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderDetailsService.getAllOrderDetails());
        tblOrderDetails.setItems(observableList);
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProducts();
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
