package uzumtech.billsplitter.billsplitter.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uzumtech.billsplitter.billsplitter.model.enums.Option;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Double price;
    List<String> clients;
    List<String> products;
    Option option;
}
