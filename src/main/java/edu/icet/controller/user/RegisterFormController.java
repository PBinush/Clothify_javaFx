package edu.icet.controller.user;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.dto.User;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.UserService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RegisterFormController {
    @FXML
    public RadioButton btnRoleAdmin;

    @FXML
    public RadioButton btnRoleEmployee;

    @FXML
    public JFXTextField txtSalary;

    @FXML
    public AnchorPane ancLogin;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFirstname;

    @FXML
    private JFXTextField txtLastname;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String name = txtFirstname.getText()+txtLastname.getText();
        String role = "";
        if (btnRoleAdmin.isSelected()){
            role = "Admin";
        }else if(btnRoleEmployee.isSelected()){
            role = "employee";
        }

        UserService userService = ServiceFactory.getInstance().getServiceTpe(ServiceType.USER);
        User user = new User(
                name,
                txtUsername.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                role
        );

        if (userService.saveUser(user)){
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/user/login_form.fxml"));
                ancLogin.getChildren().setAll(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new Alert(Alert.AlertType.INFORMATION,"registration successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }
}
