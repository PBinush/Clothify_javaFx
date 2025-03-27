package  edu.icet.controller.user;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.dto.RandomNumber;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.UserService;
import edu.icet.util.ServiceType;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.*;

public class ForgetPasswordFormController implements Initializable {

    @FXML
    private AnchorPane ancForgotPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXPasswordField txtRePassword;

    @FXML
    private TextField txtnum1;

    @FXML
    private TextField txtnum2;

    @FXML
    private TextField txtnum3;

    @FXML
    private TextField txtnum4;

    @FXML
    private TextField txtnum5;

    String randomNumber;

    UserService userService = ServiceFactory.getInstance().getServiceTpe(ServiceType.USER);
    @FXML
    void btnSendOTPOnAction(ActionEvent event) throws MessagingException {
        Random random = new Random();
        randomNumber = String.valueOf(random.nextInt(100000));
        new RandomNumber(Integer.parseInt(randomNumber));

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String recipientEmail = txtEmail.getText();
        String myEmail = "pasidbinush@gmail.com";
        String password = "qeue tvov oxij bcim";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail,password);
            }
        });

        Message message = prepareMessage(session,myEmail,recipientEmail,randomNumber);
        Transport.send(message);
        if (message!=null){
            new Alert(Alert.AlertType.INFORMATION,"OTP send successful,Please check your email");
        }else{
            new Alert(Alert.AlertType.ERROR,"Invalid email,You are NOT registered");
        }
    }

    private Message prepareMessage(Session session, String myEmail, String recipientEmail, String randomNumber) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Clothify Login OTP");
            message.setText("Your OTP Code is : "+randomNumber);
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnUpdatePasswordOnAction(ActionEvent event) {
        String concat = txtnum1.getText()+txtnum2.getText()+txtnum3.getText()+txtnum4.getText()+txtnum5.getText();
        int num1 = Integer.parseInt(concat);
        int num2 = Integer.parseInt(randomNumber);
        if(num1 == num2){
            System.out.println("ela bn");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> possibleSuggestions = new ArrayList<>();
        userService.getAll().forEach(user -> {
            possibleSuggestions.add(user.getEmail());
        });
        TextFields.bindAutoCompletion(txtEmail,possibleSuggestions);
    }
}
