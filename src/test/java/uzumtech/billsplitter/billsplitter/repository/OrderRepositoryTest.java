package uzumtech.billsplitter.billsplitter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uzumtech.billsplitter.billsplitter.dto.request.OrderRequest;
import uzumtech.billsplitter.billsplitter.dto.response.OrderResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
    }

    @Test
    void addOrder_ShouldAddOrderToList() {
        OrderRequest request = OrderRequest.builder()
                .price(120000.0)
                .clients(List.of("Fazliddin", "Anvar"))
                .products(List.of("Pizza", "Coca-Cola"))
                .build();

        orderRepository.addOrder(request);

        List<OrderResponse> result = orderRepository.getOrdersById(1L);
        assertEquals(1, result.size());

        OrderResponse response = result.get(0);
        assertEquals(120000.0, response.getPrice());
        assertEquals(2, response.getClients().size());
        assertEquals(2, response.getProducts().size());
        assertEquals("Fazliddin", response.getClients().get(0));
        assertEquals("Pizza", response.getProducts().get(0));
    }

    @Test
    void getOrdersById_ShouldReturnCorrectOrder() {
        orderRepository.addOrder(OrderRequest.builder()
                .price(50000.0)
                .clients(List.of("Kane"))
                .products(List.of("Burger"))
                .price(100000.0)
                .clients(List.of("Fazliddin", "Anvar"))
                .products(List.of("Pizza", "Coca-Cola"))
                .build());

        OrderResponse first = orderRepository.getOrdersById(1L).get(0);
        assertEquals(50000.0, first.getPrice());
        assertEquals(1, first.getClients().size());
        assertEquals("Kane", first.getClients().get(0));

        OrderResponse second = orderRepository.getOrdersById(2L).get(0);
        assertEquals(100000.0, second.getPrice());
        assertEquals(2, second.getClients().size());
        assertEquals("Fazliddin", second.getClients().get(0));
        assertEquals("Pizza", second.getProducts().get(0));
    }

    @Test
    void getOrdersById_InvalidId_ShouldThrowException() {
        orderRepository.addOrder(OrderRequest.builder()
                .price(100000.0)
                .clients(List.of("Fazliddin"))
                .products(List.of("Pizza"))
                .build());

        assertThrows(IndexOutOfBoundsException.class, () -> orderRepository.getOrdersById(2L));
    }
}
