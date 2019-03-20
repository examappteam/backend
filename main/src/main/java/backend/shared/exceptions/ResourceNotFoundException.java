package backend.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(Class c) {

        super(String.format("No resource of type %s could be found.", c.getSimpleName()));

    }

    public ResourceNotFoundException(String message, Throwable cause) {

        super(message, cause);

    }

    public ResourceNotFoundException(String message) {

        super(message);

    }

    public ResourceNotFoundException(Throwable cause) {

        super(cause);

    }

}
