package kr.co.velysound.config.exception.error;

import kr.co.velysound.config.response.ResponseMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ResponseEntity에 담을 내용 정리
 * ErrorCode 인터페이스에 geter 사용
 *
 * @author opure
 */
@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
	INVALID_PARAMETER(400, HttpStatus.BAD_REQUEST, ResponseMessage.BAD_REQUEST),
	MEHOTD_TYPE_ERROR(405, HttpStatus.METHOD_NOT_ALLOWED, ResponseMessage.METHOD_NOT_ALLOWED),
	MEDIA_TYPE_ERROR(415, HttpStatus.UNSUPPORTED_MEDIA_TYPE, ResponseMessage.UNSUPPORTED_MEDIA_TYPE),
	NO_AUTHORITY(403, HttpStatus.FORBIDDEN, ResponseMessage.FORBIDDEN),
	URL_NOT_FOUND(404, HttpStatus.NOT_FOUND, ResponseMessage.NOT_FOUND),
	INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR),
	USER_NOT_EXIST(401, HttpStatus.UNAUTHORIZED, ResponseMessage.USER_NOT_EXIST),
	USER_DISABLED(401, HttpStatus.UNAUTHORIZED, ResponseMessage.USER_DISABLED),
	PASSWORD_NOT_CORRECT(401, HttpStatus.UNAUTHORIZED, ResponseMessage.PASSWORD_NOT_CORRECT),
	SESSION_INVALID(401, HttpStatus.UNAUTHORIZED, ResponseMessage.SESSION_INVALID),
	IMPORT_EXCEL_FAILURE(500, HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.IMPORT_EXCEL_FAILURE),
	;
	private final int code;
	private final HttpStatus httpStatus;
	private final String message;
}
