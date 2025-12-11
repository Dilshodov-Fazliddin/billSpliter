package uzumtech.billsplitter.billsplitter.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import uzumtech.billsplitter.billsplitter.constant.ErrorType;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationException extends RuntimeException {

    int code;
    String message;
    HttpStatus status;
    ErrorType errorType;

    public ApplicationException(int i, String message, ErrorType internal, HttpStatus notFound) {
        super(message);
        this.code = i;
        this.status = notFound;
        this.message = message;
        this.errorType = internal;
    }
}
