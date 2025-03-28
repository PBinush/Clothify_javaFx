package edu.icet.controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UserProfileController {

    @FXML
    public AnchorPane ancProfile;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
