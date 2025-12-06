package uzumtech.billsplitter.billsplitter.service;

import uzumtech.billsplitter.billsplitter.dto.request.OrderRequest;

public interface OrderService {

   void addOrder(OrderRequest order);

   Object getOrdersById(Long id);
}
