package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.OrderDetailsDao;
import edu.icet.dto.OrderDetails;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.service.custom.OrderDetailsService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {
    final OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER_DETAILS);
    @Override
    public boolean saveOrderDetail(OrderDetails orderDetails) {
        OrderDetailsEntity map = new ModelMapper().map(orderDetails, OrderDetailsEntity.class);
        if (orderDetailsDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        List<OrderDetailsEntity> entities = orderDetailsDao.getAll();

        if (entities != null) {
            ModelMapper modelMapper = new ModelMapper();
            for (OrderDetailsEntity entity : entities) {
                orderDetails.add(modelMapper.map(entity, OrderDetails.class));
            }
        }

        return orderDetails;
    }
}
