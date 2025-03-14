package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.OrderDetailsDao;
import edu.icet.dto.OrderDetail;
import edu.icet.dto.cartTM;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.service.custom.OrderDetailsService;
import edu.icet.util.DaoType;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    final OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER_DETAILS);
    @Override
    public boolean saveOrderDetail(OrderDetail orderDetails) {
        OrderDetailsEntity map = new ModelMapper().map(orderDetails, OrderDetailsEntity.class);
        new Alert(Alert.AlertType.INFORMATION,"ooooooooooooooooooooo").show();
        if (orderDetailsDao.save(map)){
              return true;
          }
          return false;
    }

    @Override
    public List<cartTM> getAllOrderDetails() {
        List<cartTM> orderDetailsList = new ArrayList<>();
        for (OrderDetailsEntity entity : orderDetailsDao.getAll()) {
            orderDetailsList.add(
                    new ModelMapper().map(entity, cartTM.class)
            );
        }
        return orderDetailsList;
    }
}
