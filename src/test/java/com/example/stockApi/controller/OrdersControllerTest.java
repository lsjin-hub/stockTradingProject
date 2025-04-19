package com.example.stockApi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import com.example.stockApi.dto.OrdersResponseDto;
import com.example.stockApi.dto.StocksDto;
import com.example.stockApi.service.OrdersService;

@WebMvcTest(OrdersController.class)
public class OrdersControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private OrdersService ordersService;
	
	// 200
	@Test
	void shouldReturn200WhenGetOrders() throws Exception{
		 // given
        OrdersResponseDto order = new OrdersResponseDto(
                1, "user01", "홍길동", "2024-01-01", "2024-01-02",
                List.of(new StocksDto("BUY", "005930", 10, 70000, "completed", "2024-01-01"))
        );
        given(ordersService.getAllOrders()).willReturn(List.of(order));

        // when & then
        var mvcResult = mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(1))
                .andExpect(jsonPath("$[0].userName").value("홍길동"))
                .andExpect(jsonPath("$[0].stocks[0].stockCode").value("005930"))
                .andReturn();
        
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println("List 조회 성공" + responseBody);
	}
	
	// 200
	@Test
	void shouldReturn200WhenGetOrderById() throws Exception{
		// given
        OrdersResponseDto order = new OrdersResponseDto(
                1, "user01", "홍길동", "2024-01-01", "2024-01-02",
                List.of(new StocksDto("BUY", "005930", 10, 70000, "completed", "2024-01-01"))
        );
        given(ordersService.getOrderById(1)).willReturn(order);

        // when & then
        var mvcResult = mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user01"))
                .andExpect(jsonPath("$.stocks[0].quantity").value(10))
                .andReturn();
        
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println("단건 조회 성공" + responseBody);
	}
}
