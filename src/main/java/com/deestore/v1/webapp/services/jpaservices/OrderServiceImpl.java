package com.deestore.v1.webapp.services.jpaservices;

import com.deestore.v1.webapp.domains.Order;
import com.deestore.v1.webapp.repositories.OrderRepository;
import com.deestore.v1.webapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<?> listAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add); // fun with Java 8
        return orders;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return orderRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteOrderById(id);
    }
}
