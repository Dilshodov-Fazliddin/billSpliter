package uzumtech.billsplitter.billsplitter.dto.controller1;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzumtech.billsplitter.billsplitter.dto.request.OrderRequest;
import uzumtech.billsplitter.billsplitter.service.OrderService;


@RestController
@RequestMapping("/api/fazliddin's-restaurant/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest order) {
        orderService.addOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        Object ordersById = orderService.getOrdersById(id);
        return ResponseEntity.ok().body(ordersById);
    }
}
