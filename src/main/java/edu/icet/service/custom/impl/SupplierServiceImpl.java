package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.SupplierDao;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    final SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

    @Override
    public boolean saveSupplier(Supplier supplier) {
        SupplierEntity map = new ModelMapper().map(supplier, SupplierEntity.class);
        map.setId(genarateId());
        if (supplierDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (SupplierEntity entity : supplierDao.getAll()) {
            suppliers.add(
                    new ModelMapper().map(entity,Supplier.class)
            );
        }
        return suppliers;
    }

    @Override
    public String genarateId() {
        String lastId = supplierDao.getLastId();
        if (lastId == null || lastId.isEmpty()) {
            lastId = "S000";
        }

        int i = Integer.parseInt(lastId.substring(1));
        i++;

        String id = String.format("S%03d", i);
        System.out.println("Next Customer ID: " + id);
        return id;
    }

    @Override
    public Supplier getSupplierById(String id) {
        SupplierEntity supplierEntity = supplierDao.getSupplierById(id);
        return new ModelMapper().map(supplierEntity,Supplier.class);
    }

    @Override
    public boolean deleteSupplier(String id) {
        return supplierDao.delete(id);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierEntity map = new ModelMapper().map(supplier, SupplierEntity.class);
        if (supplierDao.update(map)){
            return true;
        }
        return false;
    }
}
