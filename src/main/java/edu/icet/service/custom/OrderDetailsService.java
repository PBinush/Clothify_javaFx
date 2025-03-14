package edu.icet.service.custom;

import edu.icet.dto.OrderDetail;
import edu.icet.dto.cartTM;
import edu.icet.service.SuperService;
import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean saveOrderDetail(OrderDetail orderDetails);
    List<cartTM> getAllOrderDetails();
}
