package edu.icet.controller.common.product_category;

import edu.icet.controller.cards.product_categories.GentsProductCardController;
import edu.icet.controller.common.ProductFormController;
import edu.icet.dto.Product;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.ProductService;
import edu.icet.util.ServiceType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductGentsCategoryFormController implements Initializable {
    public AnchorPane ancProductCategory;
    public GridPane productCardContainer;
    final ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);
    private List<Product> productsList;
    ProductFormController productFormController = new ProductFormController();

    public void loadGentsProducts() {
        productsList = productService.getAllGentsProduct();

        int column = 0;
        int row = 1;

        try {
            for (Product product : productsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/cards/product_categories/gents_product_card.fxml"));
                VBox cardBox = fxmlLoader.load();
                GentsProductCardController gentsProductCardController = fxmlLoader.getController();
                gentsProductCardController.setData(product);
                gentsProductCardController.setOnCardClick(productFormController::handleProductCardClick);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                productCardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadGentsProducts();
    }
}
