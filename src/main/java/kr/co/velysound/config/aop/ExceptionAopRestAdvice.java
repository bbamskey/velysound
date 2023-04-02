package kr.co.velysound.config.aop;

import kr.co.velysound.common.service.HistService;
import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.config.exception.RestApiException;
import kr.co.velysound.config.exception.error.CommonErrorCode;
import kr.co.velysound.config.exception.error.ErrorCode;
import kr.co.velysound.config.response.ErrorResponse;
import kr.co.velysound.vo.UsrVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author sjoh@mind-one.co.kr
 * @version 1.0
 * @category aop
 * @apiNote Rest Controller Exception Advice
 * RestController에서 발생하는 익셉션 처리
 * Runtime Exception, IllegalElement Exception 적용
 * 테스트 하면서 추후 필요 exception 처리기능 추가 예정
 * @since 22.03.31
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionAopRestAdvice extends ResponseEntityExceptionHandler {
	private final HistService histService;

	/**
	 * 사용자 정의 익셉션 처리 (RuntimeException 처리)
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<Object> handleRuntimeException(final RestApiException e, HttpServletRequest request) {
		setErrHist(e.getMessage(), request);
		final ErrorCode errorCode = e.getErrorCode();
		return handleExceptionInternal(errorCode);
	}

	private ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus())
				.body(makeErrorResponse(errorCode));
	}

	private ErrorResponse makeErrorResponse(final ErrorCode errorCode) {
		return ErrorResponse.builder()
				.name(errorCode.name())
				.code(errorCode.getCode())
				.message(errorCode.getMessage())
				.build();
	}

	/**
	 * 파라미터 에러 처리
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgument(final IllegalArgumentException e, HttpServletRequest request) {
		log.error("handleIllegalArgument", e);
		setErrHist(e.getMessage(), request);
		final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
		return handleExceptionInternal(errorCode, e.getMessage());
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			final MethodArgumentNotValidException e,
			final HttpHeaders headers,
			final HttpStatus status,
			final WebRequest request) {
		log.error("Method Not Valid", e);
		final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
		return handleExceptionInternal(errorCode);
	}

	private ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode, final String message) {
		return ResponseEntity.status(errorCode.getHttpStatus())
				.body(makeErrorResponse(errorCode, message));
	}

	private ErrorResponse makeErrorResponse(final ErrorCode errorCode, final String message) {
		return ErrorResponse.builder()
				.name(errorCode.name())
				.code(errorCode.getCode())
				.message(message)
				.build();
	}


	/**
	 * 그외 익셉션 처리기
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAllException(final Exception ex, HttpServletRequest request) {
		log.error("handleAllException", ex);
		setErrHist(ex.getMessage(), request);
		final ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
		return handleExceptionInternal(errorCode);
	}

	@ExceptionHandler({ClientAbortException.class})
	public void handleClientAbortException(final ClientAbortException ex, HttpServletRequest request) {
		log.error("handleClientAbortException", ex.toString());
	}

	protected void setErrHist(String message, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UsrVO loginInfo = (UsrVO) session.getAttribute("loginInfo");
		if (loginInfo != null) {
			String usrId = loginInfo.getUsrId();
			String errCtnt = message;
			String errUrl = request.getRequestURI();
			MoMap histParam = new MoMap();
			histParam.put("usrId", usrId);
			histParam.put("errCtnt", errCtnt);
			histParam.put("errUrl", errUrl);
			histService.insertErrHist(histParam);
		}

	}
}
