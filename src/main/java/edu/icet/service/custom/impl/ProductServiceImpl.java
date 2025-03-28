package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.ProductDao;
import edu.icet.dto.Product;
import edu.icet.entity.ProductEntity;
import edu.icet.service.custom.ProductService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    final ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

    @Override
    public boolean saveProduct(Product product) {
        ProductEntity map = new ModelMapper().map(product, ProductEntity.class);
        map.setId(genarateId());
        if (productDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public String getProductIdByName(String name) {
        ProductEntity productByName = productDao.getProductByName(name);
        return productByName.getId();
    }

    @Override
    public List<Product> getAllGentsProduct() {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductEntity entity : productDao.getAllGentsProduct()) {
            products.add(
                    new ModelMapper().map(entity, Product.class)
            );
        }
        return products;
    }

    @Override
    public List<Product> getAllKidsProduct() {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductEntity entity : productDao.getAllKidsProduct()) {
            products.add(
                    new ModelMapper().map(entity, Product.class)
            );
        }
        return products;
    }

    @Override
    public List<Product> getAllLadiesProduct() {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductEntity entity : productDao.getAllLadiesProduct()) {
            products.add(
                    new ModelMapper().map(entity, Product.class)
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
    public Product getProductById(String id) {
        ProductEntity productById = productDao.getProductById(id);
        return new ModelMapper().map(productById, Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductEntity entity : productDao.getAll()) {
            products.add(
                    new ModelMapper().map(entity, Product.class)
            );
        }
        return products;
    }

    @Override
    public List<String> getAllProductIds() {
        return productDao.getAllProductIds();
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity map = new ModelMapper().map(product, ProductEntity.class);
        if (productDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(String id) {
        return productDao.delete(id);
    }

}
