package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import java.util.List;

public interface OrderDao extends CrudDao<OrderEntity> {
    List<OrderEntity> getAll();
    OrderEntity getOrderById (String id);
    List<OrderEntity> getOrdersByEmployeeId(String id);
}
