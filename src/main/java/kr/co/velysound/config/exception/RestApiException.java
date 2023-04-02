package kr.co.velysound.config.exception;

import kr.co.velysound.config.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 의존성 주입 방법 (DI)
 * 1. 생성자 주입 (스프링에서(4.3이후) 권장)
 * 2. 필드 주입
 * 3. 수정자 주입
 * <p>
 * ※ 생성자 주입의 장점
 * 1) 순환 참조 방지
 * : 다른 여러 Bean들이 서로를 필요로 하는 상태
 * 2) 불변셩
 * : 필드를 final로 선언하기 때문 (런타임 시점에 변경 불가)
 * 런타입 익셉션 상속객체
 * 익셉션 발생시 에러코드 처리기를 가짐.
 *
 * @author sjoh@mind-one.co.kr
 * @category exception
 * @apiNote
 * @Getter , @Setter 지향
 * @Data 지양.
 * @Autowird 지양
 * @RequiredArgsConstructor 지향
 * > 파이널로 선언된 필드값들을 사용하는 생성자 함수를 생성
 */
@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
}
