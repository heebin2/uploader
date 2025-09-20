package flow.uploader.common.code;

import lombok.Getter;

import java.util.Optional;

@Getter
public class ProcessException extends RuntimeException {

    private final ErrorCode code;

    public ProcessException(final ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ProcessException(final ErrorCode code, final String message) {
        super(message);
        this.code = code;
    }

    public ProcessException(final ErrorCode code, final Exception exception) {
        super(getMessage(exception));
        this.code = code;
    }

    private static String getMessage(final Exception e) {
        return e.getMessage() != null
                ? e.getMessage()
                : Optional.of(e.getCause()).map(Throwable::getMessage).orElse("");
    }
}
