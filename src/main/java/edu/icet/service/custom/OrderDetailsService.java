package edu.icet.service.custom;

import edu.icet.dto.OrderDetail;
import edu.icet.service.SuperService;
import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean saveOrderDetail(OrderDetail orderDetails);
    List<OrderDetail> getAllOrderDetails();
    List<String> getOrderDetailNamesByOrderId(String orderId);
    String getOrderDetailsSumPricesByOrderId(String orderId);
//    Double getSumOfSalaryByDay(String date);
}
