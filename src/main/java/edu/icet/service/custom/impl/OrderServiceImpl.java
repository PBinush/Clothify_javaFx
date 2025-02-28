package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.OrderDao;
import edu.icet.dto.Order;
import edu.icet.service.custom.OrderService;
import edu.icet.util.DaoType;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    final OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);

    @Override
    public boolean saveOrder(Order order) {
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
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
