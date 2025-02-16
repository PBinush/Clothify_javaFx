package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.ProductEntity;

import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity> {
    List<ProductEntity> getAll();
}
