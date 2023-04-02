package kr.co.velysound.common.controller;

import kr.co.velysound.common.service.CommonCodeService;
import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.config.response.ResponseMessage;
import kr.co.velysound.config.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 김진원
 * @version 1.0
 * @description : 공통코드
 * @see <pre>
 * == 개정이력(Modification Information) ==
 *     수정일       수정자             수정내용
 *  ---------------------------------------------------------
 *  2022.04.20    김진원            최초 생성
 * </pre>
 * @since 2022.04.20
 */

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommonCodeController extends CommonParamController {

	private final CommonCodeService commonCodeService;

	/**
	 * 공통코드 그룹 조회
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/getGroupCode")
	public ResponseEntity<ResponseObject> getAssetsClft(@RequestBody MoMap param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);

		List<MoMap> list = commonCodeService.getGroupCode(param);
		responseObject.setCode(200);
		responseObject.setData(list);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}

	/**
	 * 공통코드 상세 조회
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/getDetailCode")
	public ResponseEntity<ResponseObject> getDetailCode(@RequestBody MoMap param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);

		List<MoMap> list = commonCodeService.getDetailCode(param);
		responseObject.setCode(200);
		responseObject.setData(list);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}

	/**
	 * 특정 공통코드 그룹정보 저장
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/saveGrpCdInfo")
	public ResponseEntity<ResponseObject> saveGrpCdInfo(@RequestBody List<MoMap> param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		String usrId = getLoginInfo().getUsrId();

		commonCodeService.saveGrpCdInfo(param, usrId);
		responseObject.setCode(200);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}

	/**
	 * 특정 공통코드 그룹정보 삭제
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/deleteGrpCdInfo")
	public ResponseEntity<ResponseObject> deleteGrpCdInfo(@RequestBody List<MoMap> param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);

		commonCodeService.deleteGrpCdInfo(param);
		responseObject.setCode(200);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}


	/**
	 * 특정 공통코드 상세정보 조회
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/getDtlCdInfo")
	public ResponseEntity<ResponseObject> getCodeInfo(@RequestBody MoMap param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);

		List<MoMap> list = commonCodeService.getDtlCdInfo(param);
		responseObject.setCode(200);
		responseObject.setData(list);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}

	/**
	 * 특정 공통코드 상세정보 저장
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/saveDtlCdInfo")
	public ResponseEntity<ResponseObject> saveCodeInfo(@RequestBody List<MoMap> param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		String usrId = getLoginInfo().getUsrId();

		commonCodeService.saveDtlCdInfo(param, usrId);
		responseObject.setCode(200);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}


	/**
	 * 특정 공통코드 상세정보 삭제
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/deleteDtlCdInfo")
	public ResponseEntity<ResponseObject> deleteCodeInfo(@RequestBody List<MoMap> param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);

		commonCodeService.deleteDtlCdInfo(param);
		responseObject.setCode(200);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}

	/**
	 * 코드키 사용가능 여부 체크
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/common/idCheck")
	public ResponseEntity<ResponseObject> idCheck(@RequestBody MoMap param) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);

		MoMap rlst = commonCodeService.idCheck(param);
		responseObject.setCode(200);
		responseObject.setData(rlst);
		responseObject.setMessage(ResponseMessage.SELECT_SUCCESS);

		return responseEntity;
	}

	@PostMapping(value = "/common/getLayerCode")
	public ResponseEntity<ResponseObject> getLayerCode(@RequestBody MoMap param) {
		return setResponseEntity(commonCodeService.getLayerCode(param));
	}

}
