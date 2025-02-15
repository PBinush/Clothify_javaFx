package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.ProductDao;
import edu.icet.dto.Products;
import edu.icet.entity.ProductEntity;
import edu.icet.service.custom.ProductService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class ProductServiceImpl implements ProductService {
    @Override
    public boolean saveProduct(Products product) {
        String id = "P001";
        ProductEntity map = new ModelMapper().map(product, ProductEntity.class);
        map.setId(id);
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        if (productDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public boolean searchProductByName(String name) {
        return false;
    }
}
