package edu.icet.service.custom;

import edu.icet.dto.OrderDetails;
import edu.icet.service.SuperService;
import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean saveOrderDetail(OrderDetails orderDetails);
    List<OrderDetails> getAllOrderDetails();
}
