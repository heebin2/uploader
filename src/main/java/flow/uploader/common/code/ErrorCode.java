package flow.uploader.common.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SUCCESS(0, HttpStatus.OK, null),

    BAD_REQUEST(2, HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."),
    ILLEGAL_STATE(3, HttpStatus.BAD_REQUEST, "유효하지 않은 상태 요청입니다."),
    UNAUTHENTICATED(4, HttpStatus.UNAUTHORIZED, "인증 정보가 유효하지 않습니다."),
    UNAUTHORIZED(5, HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    NOT_FOUND(6, HttpStatus.NOT_FOUND, null),
    CONFLICT(7, HttpStatus.CONFLICT, null),
    EXPIRED_ACCESS_TOKEN(8, HttpStatus.UNAUTHORIZED, "access-token 정보가 유효하지 않거나 만료되었습니다."),
    INVALID_REFRESH_TOKEN(9, HttpStatus.UNAUTHORIZED, "장기간 앱 사용이 없거나, 패스워드 변경 등의 보안정책에 따라 로그아웃 되었습니다.\n 다시 로그인하여 사용해 주세요."),
    BAD_REQUEST_CUSTOM_MESSAGE(10, HttpStatus.BAD_REQUEST, "%s"),
    TIMEOUT(900, HttpStatus.REQUEST_TIMEOUT, null),
    EXTERNAL_SERVICE_ERROR(950, HttpStatus.INTERNAL_SERVER_ERROR, "외부 시스템 연동에 실패하였습니다."),
    SERVER_ERROR(999, HttpStatus.INTERNAL_SERVER_ERROR, "일시적으로 서비스를 이용할 수 없습니다. 잠시 후 다시 시도해 주세요."),
    CLIENT_ERROR(1000, HttpStatus.INTERNAL_SERVER_ERROR, "클라이언트 오류가 발생하였습니다. 잠시 후 다시 시도해 주세요."),

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
