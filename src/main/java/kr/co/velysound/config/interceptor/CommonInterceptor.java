package kr.co.velysound.config.interceptor;

import kr.co.velysound.vo.UsrVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 공통 인터셉터
 *
 * @author opure
 */
@Slf4j
@Component
public class CommonInterceptor implements HandlerInterceptor {
	protected HttpSession session = null;

	/**
	 * 세션에 계정정보가 있는지 체크.
	 * 계정정보 없을 시 로그인 페이지로 이동
	 * 구현 필요
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		setSession(request);
		UsrVO vo = getLoginInfo();
		if (vo != null) {
			return true;
		} else {
			log.info("세션정보가 없음..");
			ModelAndView modelAndView = new ModelAndView("/login");
			modelAndView.addObject("errCode", 999);

			String returnUrl = String.valueOf(request.getRequestURI());
			modelAndView.addObject("returnUrl", returnUrl.indexOf("/login") == -1 ? returnUrl : "");
			throw new ModelAndViewDefiningException(modelAndView);
		}
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
