package kr.co.velysound.config.interceptor;

import kr.co.velysound.common.service.HistService;
import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.vo.UsrVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 이력관리 인터셉터
 *
 * @author opure
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HistoryInterceptor implements HandlerInterceptor {
	@Autowired
	private final HistService histService;
	protected HttpSession session = null;

	/**
	 * ajax 요청인지 체크.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		setSession(request);
		UsrVO vo = getLoginInfo();
		if (vo == null) return false;

		String header = request.getHeader("x-requested-with");
		if (!"XMLHttpRequest".equals(header)) {
			MoMap param = new MoMap();
			param.put("mnuGrp", "assets");//자산정보만 이력 적재
			param.put("usrId", vo.getUsrId());
			param.put("url", request.getRequestURI());

			histService.saveMnuConnHist(param);
		}

		return true;
	}

	protected UsrVO getLoginInfo() {
		UsrVO vo = null;
		if (session != null) {
			vo = (UsrVO) session.getAttribute("loginInfo");
		}
		return vo;
	}

	protected HttpSession getSession() {
		return session;
	}

	protected void setSession(HttpServletRequest request) {
		if (request != null) session = request.getSession();
	}
}
