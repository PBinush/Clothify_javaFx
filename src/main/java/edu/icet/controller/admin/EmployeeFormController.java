package edu.icet.controller.admin;

import edu.icet.dto.Employee;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EmployeeFormController {
    @FXML
    public TextField txtPosition;

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

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Employee employee = new Employee(
                null,
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
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
