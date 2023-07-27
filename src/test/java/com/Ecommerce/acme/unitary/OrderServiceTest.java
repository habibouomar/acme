package com.Ecommerce.acme.unitary;

import com.Ecommerce.acme.model.Order;
import com.Ecommerce.acme.repository.OrderRepository;
import com.Ecommerce.acme.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderService service;

    @Test
    public void mustToSendTheCorrespondingAllOrders(){
        Order order = new Order();
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(repository.findAll()).thenReturn(ordersList);
        assertEquals(ordersList, service.getAllOrders());
    }
}
