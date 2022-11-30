package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CreateOrderReq;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;

import javax.mail.MessagingException;
import java.util.List;

public interface OrderService {
    List<OrderEntity> getAll();

    OrderEntity orderConfirmed(Long orderId);

    OrderEntity beingShipped(Long orderId);

    OrderEntity cancelled(Long orderId,String reason);

    OrderEntity checkoutOrder(CreateOrderReq req) throws MessagingException;


    List<OrderEntity> listStatus(OrderStatusEnum status);

    // danh sách hóa đơn theo status và người dùng
    List<OrderEntity> listOrderStatusAndUserId(OrderStatusEnum status);


    OrderStatusEnum[] status();

    // mua lại hàng
    void reOrder(Long orderId);

    long countOrderStatus(int status);
}
