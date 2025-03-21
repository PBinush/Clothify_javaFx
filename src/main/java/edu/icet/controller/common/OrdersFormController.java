package edu.icet.controller.common;

import edu.icet.dto.Order;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.OrderDetailsService;
import edu.icet.service.custom.OrderService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersFormController implements Initializable {
    @FXML
    public TableView tblItems;

    @FXML
    public TableColumn colItems;

    @FXML
    public TableColumn colReturned;

    @FXML
    private AnchorPane anc2;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDateAndTime;

    @FXML
    private TableColumn<?, ?> colEmpId;

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
    private TableView<Order> tblOrder;

    @FXML
    private TextField txtSearch;

    OrderService orderService = ServiceFactory.getInstance().getServiceTpe(ServiceType.ORDER);
    OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceTpe(ServiceType.ORDER_DETAILS);

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDateAndTime.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colItems.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colReturned.setCellValueFactory(new PropertyValueFactory<>("isReturned"));

        loadTable();

        tblOrder.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        loadItemColumn(newValue);
                    }
                });
    }

    public void loadTable(){
        ObservableList<Order> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderService.getAllOrders());
        tblOrder.setItems(observableList);
    }

    public void loadItemColumn(Order order){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderDetailsService.getOrderDetailNamesByOrderId(order.getOrderId()));
        tblItems.setItems(observableList);
    }

    public void btnReternedOnAction(ActionEvent actionEvent) {

        Order selectedOrder = tblOrder.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            selectedOrder.setIsReturned("YES");
            if (orderService.updateIsReturnedOrder(selectedOrder.getOrderId())){
                new Alert(Alert.AlertType.INFORMATION,"returned successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR).show();
            }
            loadTable();
        }
    }
}
