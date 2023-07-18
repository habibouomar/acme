package com.Ecommerce.acme.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.Ecommerce.acme.AcmeApplication;
import com.Ecommerce.acme.controller.LandingController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AcmeApplication.class)
public class SmokeTest {

    @Autowired
    private LandingController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}

