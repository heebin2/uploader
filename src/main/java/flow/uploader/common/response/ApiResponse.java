package flow.uploader.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import flow.uploader.common.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    @JsonProperty("code")
    private ErrorCode code;

    @JsonProperty("message")
    private CharSequence message;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonIgnore
    private HttpStatus httpStatus;

    @JsonProperty("data")
    private T data;

    public ApiResponse(T data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();

        this.code(ErrorCode.SUCCESS);
    }

    public static <T> ApiResponse<T> builder(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return builder(data).code(ErrorCode.SUCCESS);
    }


    public static ApiResponse<String> fail(ErrorCode code, String message) {
        return builder(message).code(code).message(message);
    }

    public ApiResponse<T> code(final ErrorCode code) {
        this.code = code;
        this.message = code.getMessage();
        this.httpStatus = code.getHttpStatus();

        return this;
    }

    public ApiResponse<T> message(final CharSequence message) {
        this.message = message;

        return this;
    }

    public ApiResponse<T> data(final T data) {
        this.data = data;

        return this;
    }
}
