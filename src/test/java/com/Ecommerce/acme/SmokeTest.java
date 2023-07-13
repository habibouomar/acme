package com.Ecommerce.acme;

import static org.assertj.core.api.Assertions.assertThat;

import com.Ecommerce.acme.controller.LandingController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private LandingController controller;


    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}

