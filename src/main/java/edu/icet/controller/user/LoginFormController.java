package edu.icet.controller.user;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    @FXML
    void btnForgotPasswordOnAction(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/user/forget_password_form.fxml"))));
        stage.show();
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../../../view/dashboard/admin_dashboard_form.fxml"));
            ancLogin.getChildren().setAll(pane);
        }catch (IOException e){
            throw new RuntimeException(e);
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
