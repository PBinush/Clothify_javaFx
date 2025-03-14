package edu.icet.controller.cards;

import edu.icet.dto.Customer;
import edu.icet.dto.Employee;
import edu.icet.dto.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class E_S_C_CardController {

    @FXML
    public VBox vb_e_s_c;

    @FXML
    private ImageView imgAvator;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    private String cardId;
    private Consumer<String> onCardClick;

    public void setData(Customer customer){
        if(customer.getTitle().equals("Mr")){
            imgAvator.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//Employee-male.png"));
        }else {
            imgAvator.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//employee-female.png"));
        }
        cardId=customer.getId();
        lblId.setText(customer.getId());
        lblName.setText(customer.getName());
    }

    public void  setData1(Supplier supplier){
        if(supplier.getTitle().equals("Mr")){
            imgAvator.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//Employee-male.png"));
        }else {
            imgAvator.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//employee-female.png"));
        }
        cardId=supplier.getId();
        lblId.setText(supplier.getId());
        lblName.setText(supplier.getName());
    }

    public void  setData2(Employee employee){
        if(employee.getTitle().equals("Mr")){
            imgAvator.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//Employee-male.png"));
        }else {
            imgAvator.setImage(new Image("D://Git Project//ClothifyStore//src//main//resources//img//avotor//employee-female.png"));
        }
        cardId=employee.getId();
        lblId.setText(employee.getId());
        lblName.setText(employee.getName());
    }

    public void setOnCardClick(Consumer<String> listener) {
        this.onCardClick = listener;
        vb_e_s_c.setOnMouseClicked(event -> {
            if (onCardClick != null) {
                onCardClick.accept(cardId);
            }
        });
    }

}
