package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.OrderDetailsEntity;
import java.util.List;

public interface OrderDetailsDao extends CrudDao<OrderDetailsEntity> {
    String getProductIdByName(String name);
    List<OrderDetailsEntity> getAll();
    boolean save(List<OrderDetailsEntity> orderDetailsEntityList);
}
