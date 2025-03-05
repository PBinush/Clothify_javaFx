package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.OrderDao;
import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import edu.icet.service.custom.OrderService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    final OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);

    @Override
    public boolean saveOrder(Order order) {
        OrderEntity map = new ModelMapper().map(order,OrderEntity.class);
//        map.setId(genarateId());
        if (orderDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        for (OrderEntity entity : orderDao.getAll()) {
            orders.add(
                    new ModelMapper().map(entity,Order.class)
            );
        }
        return orders;
    }

    @Override
    public String genarateId() {
        String lastId = orderDao.getLastId();
        if (lastId == null || lastId.isEmpty()) {
            lastId = "C000";
        }

        int i = Integer.parseInt(lastId.substring(1));
        i++;

        String id = String.format("C%03d", i);
        System.out.println("Next Customer ID: " + id);
        return id;
    }
}
