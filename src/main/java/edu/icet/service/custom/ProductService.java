package edu.icet.service.custom;

import edu.icet.dto.Products;
import edu.icet.service.SuperService;

public interface ProductService extends SuperService {
    boolean saveProduct(Products product);
    boolean searchProductByName(String name);
}
