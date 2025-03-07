package edu.icet.controller.admin;

import edu.icet.controller.cards.E_S_C_CardController;
import edu.icet.dto.Employee;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    @FXML
    public TextField txtPosition;

    @FXML
    public ComboBox cmbTitle;

    @FXML
    public GridPane employeeCardContainer;

    @FXML
    private AnchorPane anc2;

    @FXML
    private ImageView imgAvotor;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtSearch;

    List<Employee> employeeList;
    final EmployeeService employeeService = ServiceFactory.getInstance().getServiceTpe(ServiceType.EMPLOYEE);
    @FXML
    void btnAddOnAction(ActionEvent event) {
        Employee employee = new Employee(
                null,
                cmbTitle.getValue().toString(),
                txtName.getText(),
                txtPosition.getText(),
                txtAddress.getText(),
                txtNumber.getText(),
                txtEmail.getText()
        );
        EmployeeService serviceTpe = ServiceFactory.getInstance().getServiceTpe(ServiceType.EMPLOYEE);
        if (serviceTpe.saveEmployee(employee)){
            new Alert(Alert.AlertType.INFORMATION,"employee added successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadEmployee();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(employeeService.deleteEmployee(lblID.getText())){
            new Alert(Alert.AlertType.ERROR,"Delete Successfully : "+lblID.getText()).show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadEmployee();
        clearCartDetail();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Employee employee = new Employee(
                lblID.getText(),
                cmbTitle.getValue().toString(),
                txtName.getText(),
                txtPosition.getText(),
                txtAddress.getText(),
                txtNumber.getText(),
                txtEmail.getText()
        );
        if (employeeService.updateEmployee(employee)){
            new Alert(Alert.AlertType.INFORMATION,"Update Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadEmployee();
        clearCartDetail();
    }

    public void clearCartDetail(){
        txtName.clear();
        txtNumber.clear();
        txtEmail.clear();
        txtPosition.clear();
        txtAddress.clear();
        cmbTitle.setValue(null);
    }

    public void loadEmployee(){
        employeeList = employeeService.getAllEmployee();

        int column = 0;
        int row = 1;

        try {
            for (Employee employee : employeeList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/cards/e_s_c_card.fxml"));
                VBox cardBox = fxmlLoader.load();
                E_S_C_CardController e_s_c_cardController = fxmlLoader.getController();
                e_s_c_cardController.setData2(employee);
                e_s_c_cardController.setOnCardClick(this::handleSupplierCardClick);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                employeeCardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSupplierCardClick(String employeeId) {
        System.out.println("Clicked Customer ID: " + employeeId);
        loadDetails(employeeId);
    }

    public void loadDetails(String id){
        Employee employeeById = employeeService.getEmployeeById(id);
        cmbTitle.setValue(employeeById.getTitle());
        txtName.setText(employeeById.getName());
        txtAddress.setText(employeeById.getAddress());
        txtEmail.setText(employeeById.getEmail());
        txtPosition.setText(employeeById.getPosition());
        txtNumber.setText(employeeById.getPhoneNumber());
        lblID.setText(employeeById.getId());
        if (employeeById.getTitle().equals("Mr")) {
            imgAvotor.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//Employee-male.png"));
        }else {
            imgAvotor.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//employee-female.png"));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("Mr");
        titleList.add("Miss");
        titleList.add("Mrs");
        cmbTitle.setItems(titleList);

        lblID.setText(employeeService.genarateId());
       loadEmployee();
    }
}
