package uzumtech.billsplitter.billsplitter.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import uzumtech.billsplitter.billsplitter.dto.request.OrderRequest;
import uzumtech.billsplitter.billsplitter.dto.response.OrderResponse;
import uzumtech.billsplitter.billsplitter.exception.DataNotFoundException;
import uzumtech.billsplitter.billsplitter.constant.Option;
import uzumtech.billsplitter.billsplitter.repository.OrderRepository;
import uzumtech.billsplitter.billsplitter.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;


    @Override
    public void addOrder(OrderRequest order) {
    double commission=(order.getPrice().intValue() / 100.0)*12;
        if (order.getOption()== Option.SPLIT){
            double people = order.getClients().size();
            double price=order.getPrice().intValue();

            orderRepository.addOrder(OrderRequest.builder()
                            .products(order.getProducts())
                            .clients(order.getClients())
                            .option(order.getOption())
                            .price((price+commission)/people)
                    .build());

        }else {
            orderRepository.addOrder(OrderRequest.builder()
                    .products(order.getProducts())
                    .clients(order.getClients())
                    .option(order.getOption())
                    .price(order.getPrice()+commission)
                    .build());
        }
    }

    @Override
    public Object getOrdersById(Long id) {
        List<OrderResponse> ordersById = orderRepository.getOrdersById(id);
        if (ordersById.isEmpty()){
            return new DataNotFoundException("Order not found with id " + id);
        }
        return ordersById;
    }

}
