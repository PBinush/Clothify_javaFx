package edu.icet.service.custom;

import edu.icet.dto.Supplier;
import edu.icet.service.SuperService;

public interface SupplierService extends SuperService {
    boolean SaveSupplier(Supplier supplier);
}
