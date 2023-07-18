package com.Ecommerce.acme;


import com.Ecommerce.acme.controller.LandingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AcmeApplicationTests {

	@Autowired
	private LandingController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {

		assertThat(controller).isNotNull();

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("landingPage");

		assertThat(this.mockMvc.perform(get("/")).andExpect(status().isOk()));

		assertThat(this.mockMvc.perform(get("/")).andExpect(content().string(containsString("La Philosophie ACME"))));

	}

}
