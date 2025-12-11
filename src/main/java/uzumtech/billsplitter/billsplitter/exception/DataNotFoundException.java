package uzumtech.billsplitter.billsplitter.exception;

import org.springframework.http.HttpStatus;
import uzumtech.billsplitter.billsplitter.constant.ErrorType;

public class DataNotFoundException extends ApplicationException {
    public DataNotFoundException(String message) {
        super(10011,message, ErrorType.INTERNAL, HttpStatus.NOT_FOUND);
    }
}
