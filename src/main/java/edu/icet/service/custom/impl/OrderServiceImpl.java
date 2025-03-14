package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.OrderDao;
import edu.icet.Repository.custom.OrderDetailsDao;
import edu.icet.Repository.custom.ProductDao;
import edu.icet.db.DBConnection;
import edu.icet.dto.Order;
import edu.icet.dto.OrderDetail;
import edu.icet.dto.Product;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.OrderService;
import edu.icet.service.custom.ProductService;
import edu.icet.util.DaoType;
import edu.icet.util.HibernateUtil;
import edu.icet.util.ServiceType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    final OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
    final OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER_DETAILS);
    final ProductService productService = ServiceFactory.getInstance().getServiceTpe(ServiceType.PRODUCT);
    final ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

    @Override
    public boolean saveOrder(Order order, List<OrderDetail> orderDetails) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            //Save Order Entity
            OrderEntity orderEntity = new ModelMapper().map(order, OrderEntity.class);
            session.persist(orderEntity);  // Save order first
            session.flush();  // Force Hibernate to generate orderId

            //  Prepare Lists for OrderDetails and Product Updates
            List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
            List<ProductEntity> productEntities = new ArrayList<>();

            for (OrderDetail orderDetail : orderDetails) {
                //Update Product Quantity
                String productId = orderDetail.getProductId();
                Integer qty = orderDetail.getQty();
                Product product = productService.getProductById(productId);
                Integer updateQty = product.getQty() - qty;

                product.setQty(updateQty);
                ProductEntity productEntity = new ModelMapper().map(product, ProductEntity.class);
                session.update(productEntity);
                productEntities.add(productEntity);

                //Map and Persist Order Details
                OrderDetailsEntity orderDetailsEntity = new ModelMapper().map(orderDetail, OrderDetailsEntity.class);
                orderDetailsEntity.setOrder(orderEntity);  // Set the orderId explicitly
                session.persist(orderDetailsEntity);
                orderDetailsEntities.add(orderDetailsEntity);
            }

            //Commit Transaction if Everything is Successful
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
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
            lastId = "O000";
        }

        int i = Integer.parseInt(lastId.substring(1));
        i++;

        String id = String.format("O%03d", i);
        System.out.println("Next Customer ID: " + id);
        return id;
    }
}
