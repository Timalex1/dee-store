package com.deestore.v1.webapp.domains;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;
    
    public void setUp(){
        order = new Order();
    }

    @Test
    void getId() {
        Long idVal = 4L;
        order.setId(idVal);

        assertEquals(idVal, order.getId());
    }

    @Test
    void getCustomer() {
    }

    @Test
    void getOrderDetails() {
    }
}