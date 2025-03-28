package edu.icet.service.custom;

import edu.icet.dto.Order;
import edu.icet.dto.OrderDetail;
import edu.icet.service.SuperService;
import java.util.List;

public interface OrderService extends SuperService {
    boolean saveOrder(Order order, List<OrderDetail> orderDetails);
    boolean updateIsReturnedOrder(String id);
    List<Order> getAllOrders();
    String genarateId();
    String todayOrdersBalance();
    List<Order> getOrdersByEmployeeId(String id);
}
