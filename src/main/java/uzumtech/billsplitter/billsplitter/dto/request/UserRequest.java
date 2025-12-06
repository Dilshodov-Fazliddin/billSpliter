package uzumtech.billsplitter.billsplitter.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "name is blank")
    String name;
    @NotBlank(message = "surname is blank")
    String surname;
    @NotBlank(message = "phone number is blank")
    String phoneNumber;
}
