package app.leo.matching.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyJoinedException extends HttpException{

    public AlreadyJoinedException() {
    }

    public AlreadyJoinedException(String message) {
        super(message);
    }

    public AlreadyJoinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyJoinedException(Throwable cause) {
        super(cause);
    }

    public AlreadyJoinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
