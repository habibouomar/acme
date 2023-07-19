package com.Ecommerce.acme.unitary;

import com.Ecommerce.acme.model.Product;
import com.Ecommerce.acme.repository.ProductRepository;
import com.Ecommerce.acme.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @Test
    public void mustToSendTheCorrespondingProductById(){
        Product product = new Product();
        product.setId_product(1);

        Optional result = Optional.of(product);

        when(repository.findById(1)).thenReturn(result);
        Assertions.assertEquals(result, service.getProduct(1));
    }

    @Test
    public void mustToSendTheCorrespondingAllProducts(){
        Product product = new Product();
        List<Product> productsList = new ArrayList<>();
        productsList.add(product);

        when(repository.findAll()).thenReturn(productsList);
        assertEquals(productsList, service.getAllProduct());
    }
}
