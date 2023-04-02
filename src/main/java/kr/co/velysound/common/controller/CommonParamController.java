package kr.co.velysound.common.controller;

import kr.co.velysound.config.response.ResponseMessage;
import kr.co.velysound.config.response.ResponseObject;
import kr.co.velysound.vo.UsrVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonParamController {
	private HttpSession session = null;

	/**
	 * 공통 파라미터로 삼을 데이터 모델에 등록
	 *
	 * @param model
	 */
	@ModelAttribute
	public void setCommonParam(Model model, HttpServletRequest request, HttpServletResponse response) {
		setSession(request);
		model.addAttribute("contextPath", request.getServletPath());
		//권한체크
		Map<String, String> param = new HashMap<String, String>();
		param.put("url", request.getServletPath());

		//model.addAttribute("program", manageMenuService.searchProgram(param));
	}

	private HttpSession getSession() {
		return session;
	}

	private void setSession(HttpServletRequest request) {
		if (request != null) {
			session = request.getSession();
		}
	}

	protected UsrVO getLoginInfo() {
		HttpSession session = getSession();
		UsrVO logionVo = null;
		if (session != null) {
			logionVo = (UsrVO) session.getAttribute("loginInfo");
		}
		return logionVo;
	}

	//리스트 조회 결과
	protected ResponseEntity<ResponseObject> setResponseEntity(List<?> list) {
		int resultCode = 200;
		String resultMsg = ResponseMessage.SELECT_SUCCESS;
		if (list.size() == 0) {
			resultCode = 204;
			resultMsg = ResponseMessage.NO_CONTENT;
		}
		ResponseObject responseObject = new ResponseObject();
		responseObject.setCode(resultCode);
		responseObject.setData(list);
		responseObject.setMessage(resultMsg);
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		return responseEntity;
	}

	protected ResponseEntity<ResponseObject> setResponseEntity(Object obj) {
		int resultCode = 200;
		String resultMsg = ResponseMessage.SELECT_SUCCESS;
		if (obj == null) {
			resultCode = 204;
			resultMsg = ResponseMessage.NO_CONTENT;
		}
		ResponseObject responseObject = new ResponseObject();
		responseObject.setCode(resultCode);
		responseObject.setData(obj);
		responseObject.setMessage(resultMsg);
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		return responseEntity;
	}

	// 추가, 수정 , 삭제 , 저장
	protected ResponseEntity<ResponseObject> setResponseEntity(String job) {
		int resultCode = 200;
		String resultMsg = "";
		switch (job) {
			case "insert":
				resultMsg = ResponseMessage.INSERT_SUCCESS;
				break;
			case "save":
			case "update":
				resultMsg = ResponseMessage.SAVE_SUCCESS;
				break;
			case "delete":
				resultMsg = ResponseMessage.DELETE_SUCCESS;
				break;
		}
		ResponseObject responseObject = new ResponseObject();
		responseObject.setCode(resultCode);
		responseObject.setMessage(resultMsg);
		return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
	}

	// 추가, 수정 , 삭제 , 저장 후 데이터 반환
	protected ResponseEntity<ResponseObject> setResponseEntity(String job, Object obj) {
		int resultCode = 200;
		String resultMsg = "";
		switch (job) {
			case "insert":
				resultMsg = ResponseMessage.INSERT_SUCCESS;
				break;
			case "save":
			case "update":
				resultMsg = ResponseMessage.SAVE_SUCCESS;
				break;
			case "delete":
				resultMsg = ResponseMessage.DELETE_SUCCESS;
				break;
		}
		ResponseObject responseObject = new ResponseObject();
		responseObject.setCode(resultCode);
		responseObject.setData(obj);
		responseObject.setMessage(resultMsg);
		return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
	}
}
