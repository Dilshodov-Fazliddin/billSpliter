package uzumtech.billsplitter.billsplitter.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    Long id;
    String name;
    String surname;
    String phoneNumber;
}
