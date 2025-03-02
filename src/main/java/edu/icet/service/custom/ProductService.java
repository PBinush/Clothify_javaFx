package edu.icet.service.custom;

import edu.icet.dto.Product;
import edu.icet.service.SuperService;
import java.util.List;

public interface ProductService extends SuperService {
    boolean saveProduct(Product product);
    boolean searchProductByName(String name);
    List<Product> getAllGentsProduct();
    List<Product> getAllKidsProduct();
    List<Product> getAllLadiesProduct();
    String genarateId();
    Product getProductById(String id);
    List<Product> getAllProducts();
    List<String> getAllProductIds();
    boolean updateProduct(Product product);
    boolean deleteProduct(String id);

}
