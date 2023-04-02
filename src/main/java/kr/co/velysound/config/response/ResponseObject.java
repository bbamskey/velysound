package kr.co.velysound.config.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 정상 응답시
 * 데이터 및 코드, 메시지 송출 객체
 *
 * @author sjoh@mind-one.co.kr
 */
@Setter
@Getter
public class ResponseObject {
	private int code;
	private String message;
	private Object data;
}
