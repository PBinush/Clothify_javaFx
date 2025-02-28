package edu.icet.service.custom;

import edu.icet.dto.Customer;
import edu.icet.dto.Supplier;
import edu.icet.service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    boolean saveSupplier(Supplier supplier);
    List<Supplier> getAllSuppliers();
    String genarateId();
    Supplier getSupplierById(String id);
    boolean deleteSupplier(String id);
    boolean updateSupplier(Supplier supplier);
}
