package com.Ecommerce.acme.unitary;

import com.Ecommerce.acme.model.Order;
import com.Ecommerce.acme.repository.OrderRepository;
import com.Ecommerce.acme.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderService service;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @Test
    public void testGetAllOrders(){
        Order order = new Order();
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(repository.findAll()).thenReturn(ordersList);
        assertEquals(ordersList, service.getAllOrders());
    }

    @Test
    public void testInsertOrder() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        Order order = new Order();
        order.setId_order(1);
        order.setOrder_date(formattedDate);
        order.setValidate(false);

        service.insertOrder(order);

        verify(repository).save(orderArgumentCaptor.capture());

        Order capturedOrder = orderArgumentCaptor.getValue();

        assertEquals(order, capturedOrder);
    }
}
