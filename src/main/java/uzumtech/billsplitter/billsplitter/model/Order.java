package uzumtech.billsplitter.billsplitter.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uzumtech.billsplitter.billsplitter.constant.Option;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Order {
    Long id;
    Double price;
    List<String> products;
    List<String> clients;
    Option option;
}
