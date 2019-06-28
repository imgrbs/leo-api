package app.leo.matching.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongPeriodException extends HttpException {
    public WrongPeriodException() {
    }

    public WrongPeriodException(String message) {
        super(message);
    }

    public WrongPeriodException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPeriodException(Throwable cause) {
        super(cause);
    }

    public WrongPeriodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
