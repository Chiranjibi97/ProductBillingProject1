package com.nt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@DisplayName("Spring Boot Product Test Case")
@TestMethodOrder(OrderAnnotation.class)
class ProductBillingProject1ApplicationTests {

	private Logger logger = LoggerFactory.getLogger(ProductBillingProject1ApplicationTests.class);
	@Autowired
	private MockMvc mock;

	@Test
	@DisplayName("Save Product Test")
	@Disabled
	@Order(2)
	public void testSaveProduct() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/rest/save")
				.header("Content-Type", "application/json").content(
						"{\"productName\" : \"pen\",\"prodCode\" : \"p-9865\",\"prodCost\" : 10.0,\"prodDiscount\" : 20.0,\"prodGst\": 12.0}");
		MvcResult result = mock.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertNotNull(response.getContentAsString());
		if (!response.getContentAsString().contains("Register successfully")) {
			Assertions.fail();
		}
		logger.info(response.getContentAsString());
	}

	@Test
	@Disabled
	@DisplayName("Update Product Test")
	@Order(1)
	public void TestUpdateProduct() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/rest/modify")
				.header("Content-Type", "application/json").content(
						"{\"prodId\" : 2,\"productName\" : \"pen\",\"prodCode\" : \"p-9865\",\"prodCost\" : 10.0,\"prodDiscount\" : 20.0,\"prodGst\": 12.0}");
		MvcResult result = mock.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertNotNull(response.getContentAsString());

		if (!response.getContentAsString().contains("Product value Modifyed")) {
			Assertions.fail();
		}
	}
	
	@Test
	@Disabled
	public void testGetProductById() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/rest/one/1");
		MvcResult result = mock.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		Assertions.assertEquals(HttpStatus.OK.value(),response.getStatus());
		Assertions.assertNotNull(response.getContentAsString());
		
		if(!response.getContentType().contains("application/json")) {
			   Assertions.fail();
		}
		logger.info(response.getContentAsString());
	}
	
	@Test
	@Disabled
	public void TestGetAllProduct() throws Exception {
	     MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/rest/getAll");
	     MvcResult result = mock.perform(request).andReturn();
	     MockHttpServletResponse response = result.getResponse();
	     
	     Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	     Assertions.assertNotNull(response.getContentAsString());
	     
	     if(!response.getContentType().contains("application/json")) {
	    	 Assertions.fail();
	     }
	     logger.info(response.getContentAsString());
	}
	
	@Test
	@DisplayName("Delete Product Test")
	public void TestDeleteProductById() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/rest/delete/1");
		MvcResult result = mock.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assertions.assertNotNull(response.getContentAsString());
		
		if(!response.getContentAsString().contains("Product deleted")) {
			Assertions.fail();
		}
	}

}
