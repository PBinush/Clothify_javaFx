package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.OrderDetailsEntity;
import java.util.List;

public interface OrderDetailsDao extends CrudDao<OrderDetailsEntity> {
    List<OrderDetailsEntity> getAll();
}
