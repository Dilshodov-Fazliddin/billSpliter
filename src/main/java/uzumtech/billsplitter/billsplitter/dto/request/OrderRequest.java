package uzumtech.billsplitter.billsplitter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "price is blank")
    Double price;
    @NotNull(message = "product is blank")
    Long productId;
    @NotEmpty(message = "clients are blank")
    List<String> clients;
    @NotEmpty(message = "products are blank")
    List<String> products;
    @NotNull(message = "option is blank")
    Option option;
}
