package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.OrderDetailsDao;
import edu.icet.dto.OrderDetail;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.service.custom.OrderDetailsService;
import edu.icet.util.DaoType;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public List<OrderDetail> getAllOrderDetails() {
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        for (OrderDetailsEntity entity : orderDetailsDao.getAll()) {
            orderDetailsList.add(
                    new ModelMapper().map(entity, OrderDetail.class)
            );
        }
        return orderDetailsList;
    }

    @Override
    public List<String> getOrderDetailNamesByOrderId(String orderId) {
        List<String> orderDetailList = new ArrayList<>();
        orderDetailsDao.getAll().forEach(entity -> {
            if (entity.getOrder().getOrderId().equals(orderId)){
                orderDetailList.add(entity.getProductId().getName());
            }
        });
        return orderDetailList;
    }

    @Override
    public String getOrderDetailsSumPricesByOrderId(String orderId) {
        AtomicReference<Double> sumOfPrice= new AtomicReference<>((double) 0);
        String sum ;
        orderDetailsDao.getAll().forEach(entity -> {
                if (entity.getOrder().getOrderId().equals(orderId)){
                    sumOfPrice.set(entity.getPrice());
                }
        });
        return sumOfPrice.toString();
    }

//    @Override
//    public Double getSumOfSalaryByDay(String date) {
//        AtomicReference<Double> salary = new AtomicReference<>(0.0);
//        orderDetailsDao.getOrderDetailsByOrderDate(date).forEach(
//                entity -> salary.updateAndGet(v -> v + entity.getPrice())
//        );
//        return salary;
//    }

}
