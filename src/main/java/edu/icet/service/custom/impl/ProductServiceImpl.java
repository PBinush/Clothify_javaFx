package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.SuperDao;
import edu.icet.Repository.custom.ProductDao;
import edu.icet.dto.Products;
import edu.icet.entity.ProductEntity;
import edu.icet.service.custom.ProductService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    final ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

    @Override
    public boolean saveProduct(Products product) {
        ProductEntity map = new ModelMapper().map(product, ProductEntity.class);
        map.setId(genarateId());
        if (productDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public boolean searchProductByName(String name) {
        return false;
    }

    @Override
    public List<Products> getAllProducts() {
        ArrayList<Products> products = new ArrayList<>();
        for (ProductEntity entity : productDao.getAll()) {
            products.add(
                    new ModelMapper().map(entity,Products.class)
            );
        }
        return products;
    }

    @Override
    public String genarateId(){
        String lastId = productDao.getLastId();
        if (lastId == null || lastId.isEmpty()) {
            lastId = "P000";
        }

        int i = Integer.parseInt(lastId.substring(1));
        i++;

        String id = String.format("P%03d", i);
        System.out.println("Next Product ID: " + id);
        return id;
    }

    @Override
    public Products getProductById(String id) {
        ProductEntity productById = productDao.getProductById(id);
        return new ModelMapper().map(productById, Products.class);
    }
}
