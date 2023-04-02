package kr.co.velysound.config.listener;

import kr.co.velysound.common.service.HistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionChkListener implements HttpSessionListener {

	private final HistService histService;

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		Integer histId = (Integer) session.getAttribute("histId");
		log.debug("로그인 종료 HIST_ID : {}", histId);
		if (histId != null) histService.updateLoginHist(histId);
	}
}
