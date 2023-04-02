package kr.co.velysound.common.controller;

import kr.co.velysound.common.service.CommonService;
import kr.co.velysound.common.service.HistService;
import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.config.response.ResponseMessage;
import kr.co.velysound.config.response.ResponseObject;
import kr.co.velysound.vo.UsrVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 김진원
 * @version 1.0
 * @description : 공통
 * @see <pre>
 * == 개정이력(Modification Information) ==
 * 	 수정일	   수정자			 수정내용
 *  ---------------------------------------------------------
 *  2022.05.02	김진원			최초 생성
 * </pre>
 * @since 2022.05.02
 */

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommonController extends CommonParamController {

	private final HistService histService;
	private final CommonService commonService;

	/**
	 * 그리드 엑셀 내보내기
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/common/gridExcelDownLoad")
	public void gridExcelDownLoad(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		String fileName = request.getParameter("fileName");
		fileName = fileName.replaceAll("\r", "").replaceAll("\n", "");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int j = 0;

		while (true) {
			String data = request.getParameter("fileData" + j);
			data = data.replaceAll("\r", "").replaceAll("\n", "");
			if (data != null && data.length() > 0) {
				byte[] dataByte = Base64.decodeBase64(data.getBytes());
				outputStream.write(dataByte);
				j++;
			} else
				break;
		}

		if (outputStream.size() > 0) {
			response.reset();
			response.setContentType("application/octet-stream");
			String client = request.getHeader("User-Agent");
			if (client.indexOf("MSIE") != -1 || client.indexOf("Trident") != -1)
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "8859_1").replaceAll("\\+", "%20"));
			else
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Length", String.valueOf(outputStream.size()));

			ServletOutputStream out = response.getOutputStream();

			out.write(outputStream.toByteArray());
			out.flush();
			out.close();
		}

		responseObject.setCode(200);
	}

	/**
	 * 엑셀다운 이력 추가
	 */
	@PostMapping("/common/saveExcelDownHist")
	public ResponseEntity<ResponseObject> saveExcelDownHist(@RequestBody MoMap param, HttpSession session) {
		UsrVO usrInfo = (UsrVO) session.getAttribute("loginInfo");
		param.put("loginId", usrInfo.getUsrId());
		return setResponseEntity("insert");
	}

	/**
	 * 출력 이력 추가
	 *
	 * @param param
	 * @param session
	 * @return
	 */
	@PostMapping("/common/saveExportHist")
	public ResponseEntity<ResponseObject> saveExportHist(@RequestBody MoMap param, HttpSession session) {
		UsrVO usrInfo = (UsrVO) session.getAttribute("loginInfo");
		param.put("loginId", usrInfo.getUsrId());
		histService.saveExportHist(param);
		return setResponseEntity("insert");
	}

	/**
	 * 입출력 이력 추가
	 *
	 * @param param
	 * @param session
	 * @return
	 */
	@PostMapping("/common/saveFileIpttHist")
	public ResponseEntity<ResponseObject> saveFileIpttHist(@RequestBody MoMap param, HttpSession session) {
		UsrVO usrInfo = (UsrVO) session.getAttribute("loginInfo");
		param.put("loginId", usrInfo.getUsrId());
		histService.saveFileIpttHist(param);
		return setResponseEntity("insert");
	}

	/**
	 * 즐겨찾기 조회
	 *
	 * @param param
	 * @param session
	 * @return
	 */
	@PostMapping("/common/getListBkmk")
	public ResponseEntity<ResponseObject> getListBkmk(@RequestBody MoMap param, HttpSession session) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		int resultCode = 200;
		String resultMsg = ResponseMessage.SELECT_SUCCESS;

		UsrVO usrInfo = (UsrVO) session.getAttribute("loginInfo");
		String usrId = usrInfo.getUsrId();
		param.put("loginId", usrId);

		List<MoMap> result = commonService.getListBkmk(param);
		if (result.size() == 0) {
			resultCode = 204;
			resultMsg = ResponseMessage.NO_CONTENT;
		}
		responseObject.setData(result);
		responseObject.setCode(resultCode);
		responseObject.setMessage(resultMsg);
		return responseEntity;
	}

	/**
	 * 즐겨찾기 추가
	 *
	 * @param param
	 * @param session
	 * @return
	 */
	@PostMapping("/common/saveBkmk")
	public ResponseEntity<ResponseObject> saveBkmk(@RequestBody MoMap param, HttpSession session) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		int resultCode = 200;
		String resultMsg = ResponseMessage.SAVE_SUCCESS;

		UsrVO usrInfo = (UsrVO) session.getAttribute("loginInfo");
		String usrId = usrInfo.getUsrId();
		param.put("loginId", usrId);

		commonService.saveBkmk(param);

		responseObject.setData(param);
		responseObject.setCode(resultCode);
		responseObject.setMessage(resultMsg);
		return responseEntity;
	}

	/**
	 * 즐겨찾기 삭제
	 *
	 * @param param
	 * @param session
	 * @return
	 */
	@PostMapping("/common/deleteBkmk")
	public ResponseEntity<ResponseObject> deleteBkmk(@RequestBody MoMap param, HttpSession session) {
		ResponseObject responseObject = new ResponseObject();
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		int resultCode = 200;
		String resultMsg = ResponseMessage.SAVE_SUCCESS;

		UsrVO usrInfo = (UsrVO) session.getAttribute("loginInfo");
		String usrId = usrInfo.getUsrId();
		param.put("loginId", usrId);
		commonService.deleteBkmk(param);

		responseObject.setData(param);
		responseObject.setCode(resultCode);
		responseObject.setMessage(resultMsg);
		return responseEntity;
	}

}
