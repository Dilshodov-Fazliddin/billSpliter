package uzumtech.billsplitter.billsplitter.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uzumtech.billsplitter.billsplitter.model.enums.Option;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    @NotBlank(message = "price is blank")
    Double price;
    @NotBlank(message = "product is blank")
    Long productId;
    @NotBlank(message = "clients are blank")
    List<String> clients;
    @NotBlank(message = "products are blank")
    List<String> products;
    @NotBlank(message = "option is blank")
    Option option;
}
