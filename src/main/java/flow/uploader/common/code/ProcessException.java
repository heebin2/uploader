package flow.uploader.common.code;

import lombok.Getter;

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
}
