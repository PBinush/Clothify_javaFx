package edu.icet.controller.user;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.dto.User;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.UserService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;

public class RegisterFormController {
    @FXML
    public RadioButton btnRoleAdmin;

    @FXML
    public RadioButton btnRoleEmployee;

    @FXML
    public JFXTextField txtSalary;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFirstname;

    @FXML
    private JFXTextField txtLastname;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtRePassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String name = txtFirstname.getText()+txtLastname.getText();
        String password = "";
        if (txtPassword.equals(txtRePassword)){
            password = txtPassword.getText();
        }
        String role = "";
        if (btnRoleAdmin.isSelected()){
            role = "Admin";
        }else if(btnRoleEmployee.isSelected()){
            role = "employee";
        }

        UserService userService = ServiceFactory.getInstance().getServiceTpe(ServiceType.USER);
        User user = new User(
                name,
                txtEmail.getText(),
                txtUsername.getText(),
                password,
                role,
                Double.parseDouble(txtSalary.getText())
        );
        if (userService.saveUser(user)){
            new Alert(Alert.AlertType.INFORMATION,"registration successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }



   
}
