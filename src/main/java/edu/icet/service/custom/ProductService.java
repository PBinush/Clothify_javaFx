package edu.icet.service.custom;

import edu.icet.dto.Products;
import edu.icet.service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean saveProduct(Products product);
    boolean searchProductByName(String name);
    List<Products> getAllProducts();
    String genarateId();
    Products getProductById(String id);
}
