package uzumtech.billsplitter.billsplitter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uzumtech.billsplitter.billsplitter.dto.request.OrderRequest;
import uzumtech.billsplitter.billsplitter.dto.response.OrderResponse;
import uzumtech.billsplitter.billsplitter.exception.DataNotFoundException;
import uzumtech.billsplitter.billsplitter.repository.OrderRepository;
import uzumtech.billsplitter.billsplitter.service.impl.OrderServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


import java.util.List;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrderSplitPrice() {
        OrderRequest request = OrderRequest.builder()
                .price(120000.0)
                .clients(List.of("Fazliddin", "Anvar", "Malika"))
                .products(List.of("Pizza"))
                .build();

        orderService.addOrder(request);

        ArgumentCaptor<OrderRequest> captor = ArgumentCaptor.forClass(OrderRequest.class);
        verify(orderRepository, times(1)).addOrder(captor.capture());

        OrderRequest saved = captor.getValue();
        assertEquals(40000.0, saved.getPrice());
        assertEquals(3, saved.getClients().size());
    }

    @Test
    void addOrderFullPrice() {
        OrderRequest request = OrderRequest.builder()
                .price(120000.0)
                .clients(List.of("Fazliddin", "Anvar"))
                .build();

        orderService.addOrder(request);

        ArgumentCaptor<OrderRequest> captor = ArgumentCaptor.forClass(OrderRequest.class);
        verify(orderRepository, times(1)).addOrder(captor.capture());

        OrderRequest saved = captor.getValue();
        assertEquals(120000.0, saved.getPrice());
    }

    @Test
    void getOrdersByIdFound() {
        List<OrderResponse> mockResponse = List.of(
                OrderResponse.builder()
                        .clients(List.of("Fazliddin", "Anvar"))
                        .products(List.of("Pizza"))
                        .build()
        );

        when(orderRepository.getOrdersById(1L)).thenReturn(mockResponse);

        Object result = orderService.getOrdersById(1L);
        List<OrderResponse> orders = (List<OrderResponse>) result;
        assertEquals(1, orders.size());
        assertEquals("SPLIT", orders.get(0).getOption());
    }

    @Test
    void getOrdersByIdNotFound() {
        when(orderRepository.getOrdersById(1L)).thenReturn(List.of());

        Object result = orderService.getOrdersById(1L);
        assertTrue(result instanceof DataNotFoundException);
        DataNotFoundException exception = (DataNotFoundException) result;
        assertEquals("Order not found with id 1", exception.getMessage());
    }
}
