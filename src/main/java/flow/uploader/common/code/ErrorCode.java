package flow.uploader.common.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SUCCESS(0, HttpStatus.OK, null),

    BAD_REQUEST(1, HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."),
    NOT_FOUND(2, HttpStatus.NOT_FOUND, null),
    SERVER_ERROR(999, HttpStatus.INTERNAL_SERVER_ERROR, "일시적으로 서비스를 이용할 수 없습니다. 잠시 후 다시 시도해 주세요."),

    DUPLICATE_EXTENSION_FILTER(1000, HttpStatus.CONFLICT, "확장자가 중복됩니다."),
    INVALID_EXTENSION_FILTER(1002, HttpStatus.BAD_REQUEST, "유효하지 않은 확장자입니다."),
    INVALID_FILE_NAME(1003, HttpStatus.BAD_REQUEST, "유효하지 않은 파일명입니다."),
    NOT_ALLOW_EXTENSION_FILE(1004, HttpStatus.BAD_REQUEST, "허용되지 않은 확장자의 파일입니다."),
    EXCEED_MAX_EXTENSION_FILTER(1005, HttpStatus.BAD_REQUEST, "확장자 필터는 최대 10개까지 등록할 수 있습니다.");

    private final int id;

    private final HttpStatus httpStatus;

    private final String message;

    @JsonValue
    public int getId() {
        return this.id;
    }
}
