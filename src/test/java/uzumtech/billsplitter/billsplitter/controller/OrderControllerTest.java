package uzumtech.billsplitter.billsplitter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import uzumtech.billsplitter.billsplitter.dto.request.OrderRequest;
import uzumtech.billsplitter.billsplitter.dto.response.OrderResponse;
import uzumtech.billsplitter.billsplitter.model.enums.Option;
import uzumtech.billsplitter.billsplitter.service.OrderService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)

class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getOrdersByIdTest() throws Exception {

        List<OrderResponse> ordersList = List.of(
                OrderResponse.builder()
                        .option(Option.valueOf("SPLIT"))
                        .clients(List.of("Fazliddin", "Anvar"))
                        .products(List.of("Pizza"))
                        .build()
        );

        when(orderService.getOrdersById(1L)).thenReturn(ordersList);

        mockMvc.perform(get("/fazliddin's-restaurant/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].option").value("SPLIT"))
                .andExpect(jsonPath("$[0].clients[0]").value("Fazliddin"))
                .andExpect(jsonPath("$[0].products[0]").value("Pizza"));
    }


    @Test
    void createOrderTest() throws Exception {

        OrderRequest request = OrderRequest.builder()
                .price(150000.0)
                .clients(List.of("Fazliddin", "Anvar"))
                .products(List.of())
                .option(Option.valueOf("SPLIT"))
                .build();

        mockMvc.perform(post("/fazliddin's-restaurant/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
