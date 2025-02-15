package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.SupplierDao;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public boolean SaveSupplier(Supplier supplier) {

        String id = "S001";
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        entity.setId(id);
        SupplierDao daoType = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        System.out.println(supplier);
        System.out.println(entity);
        if (daoType.save(entity)){
            return true;
        }
        return false;
    }
}
