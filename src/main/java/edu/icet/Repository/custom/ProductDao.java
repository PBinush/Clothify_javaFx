package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.entity.ProductEntity;
import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity> {
    List<ProductEntity> getAllGentsProduct();
    List<ProductEntity> getAllKidsProduct();
    List<ProductEntity> getAllLadiesProduct();
    ProductEntity getProductById(String id);
    List<ProductEntity> getAll();
    List<String> getAllProductIds();
    ProductEntity getProductByName(String name);
    boolean update(List<ProductEntity> productEntityList);
}
