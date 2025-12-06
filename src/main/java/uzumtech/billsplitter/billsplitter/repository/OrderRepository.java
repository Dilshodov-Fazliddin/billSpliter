package uzumtech.billsplitter.billsplitter.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import uzumtech.billsplitter.billsplitter.dto.request.OrderRequest;
import uzumtech.billsplitter.billsplitter.dto.response.OrderResponse;
import uzumtech.billsplitter.billsplitter.model.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public void addOrder(OrderRequest orderRequest) {
        Order build = Order.builder()
                .price(orderRequest.getPrice())
                .id((long) orders.size() + 1)
                .clients(orderRequest.getClients())
                .option(orderRequest.getOption())
                .products(orderRequest.getProducts())
                .build();

        orders.add(build);

        log.info("Added order {}", build);
    }

    public List<OrderResponse> getOrdersById(Long id) {

        Order order = orders.get(id.intValue() - 1);

        return List.of(OrderResponse.builder()
                .option(order.getOption())
                .clients(order.getClients())
                .price(order.getPrice())
                .products(order.getProducts())
                .build());
    }
}
