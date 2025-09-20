package flow.uploader.common.config;

import flow.uploader.common.code.ErrorCode;
import flow.uploader.common.code.ProcessException;
import flow.uploader.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private String getBindExceptionErrorMessage(BindException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " +
                        (fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "필드 검증 오류"))
                .collect(Collectors.joining(", "));

        if (errorMessage.isEmpty()) {
            return getMessage(ex);
        }

        return errorMessage;
    }

    @ExceptionHandler(ProcessException.class)
    public ApiResponse<String> handleProcessException(ProcessException ex) {
        log.error("ProcessException: {}", ex.getMessage());
        return ApiResponse.fail(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleException(Exception ex, HttpServletRequest request) {
        log.error("Exception: {}", getMessage(ex), ex);

        return ApiResponse.fail(this.exceptionToCode(ex), getMessage(ex));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ApiResponse<String> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.error("NoResourceFoundException: {}", ex.getMessage());

        return ApiResponse.fail(ErrorCode.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ApiResponse<String> handleBindException(BindException ex) {
        log.error("BindException: {}", ex.getMessage());
        return ApiResponse.fail(ErrorCode.BAD_REQUEST, getBindExceptionErrorMessage(ex));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Illegal argument: {}", ex.getMessage());
        return ApiResponse.fail(ErrorCode.BAD_REQUEST, "잘못된 요청입니다.");
    }

    private ErrorCode exceptionToCode(Exception ex) {
        if (this.isIllegalException(ex)) {
            return ErrorCode.BAD_REQUEST;
        }

        return ErrorCode.SERVER_ERROR;
    }

    private String getMessage(Exception ex) {
        if (ex.getMessage() != null) {
            return ex.getMessage();
        }

        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            return ex.getCause().getMessage();
        }

        return null;
    }

    private boolean isIllegalException(Exception ex) {
        return (ex instanceof IllegalArgumentException
                || ex instanceof IllegalStateException
                || ex instanceof BindException);
    }
}
