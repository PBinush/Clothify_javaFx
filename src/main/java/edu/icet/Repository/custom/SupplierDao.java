package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.SupplierEntity;
import java.util.List;

public interface SupplierDao extends CrudDao<SupplierEntity> {
    List<SupplierEntity> getAll();
    SupplierEntity getSupplierById(String id);
}
