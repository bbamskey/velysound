package kr.co.velysound.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 빌더 패턴
 * 객체 생성시에 표현방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
 *
 * @author opure
 */
@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
	private final int code;
	private final String name;
	private final String message;

	/**
	 * JsonInclude
	 * : ALWAYS (default) > 모든 값 출력
	 * : NON_NULL > null값 제외
	 * : NON_ABSENT > 참조유형 제외 (null 제외)
	 * : NON_EMPTY
	 * > null 제외, 참조유형 제외, Collection isEmpty() === true 제외
	 * , Array.length === 0 제외, String.length === 0 제외
	 * 등등..
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final List<ValidationError> errors;

	@Getter
	@Builder
	@RequiredArgsConstructor
	public static class ValidationError {
		private final String field;
		private final String message;

		/**
		 * @param fieldError : 문제코드, 필드 이름, 필드 메세지, 실패 이유 정보 객체
		 */
		public static ValidationError of(final FieldError fieldError) {
			return ValidationError.builder()
					.field(fieldError.getField())
					.message(fieldError.getDefaultMessage())
					.build();
		}
	}
}
