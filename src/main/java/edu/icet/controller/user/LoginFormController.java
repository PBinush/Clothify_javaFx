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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane ancLogin;
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    UserService userService = ServiceFactory.getInstance().getServiceTpe(ServiceType.USER);
    @FXML
    void btnForgotPasswordOnAction(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/user/forget_password_form.fxml"))));
        stage.show();
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        boolean userFound = false;

        for (User user : userService.getAll()) {
            if (user.getEmail().equals(txtEmail.getText()) && user.getPassword().equals(txtPassword.getText())) {
                userFound = true;
                if (user.getRole().equals("Admin")){
                    try {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/dashboard/admin_dashboard_form.fxml"));
                        ancLogin.getChildren().setAll(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Error loading dashboard").show();
                    }
                }else{
                    try {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/dashboard/employee_dashboard_form.fxml"));
                        ancLogin.getChildren().setAll(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Error loading dashboard").show();
                    }
                }
                break; // Stop checking other users
            }
        }

        if (!userFound) {
            new Alert(Alert.AlertType.ERROR, "Password or email not found").show();
        }
    }

    @FXML
    void btnregisterOnACTION(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/user/registration_form.fxml"));
            ancLogin.getChildren().setAll(pane);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
