package kr.co.velysound.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class WAMSUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(WAMSUtil.class);
	private static final String[] SQL_KEY_WORDS = {"INSERT", "UPDATE", "DELETE", "TRUNCATE", "CREATE", "DROP", "ALTER", "GRANT", "REVOKE", "COMMIT", "ROLLBACK", "ADD", "CHANGE", "SAVEPOINT", "RENAME", "BEGIN"};

	public static Date dateCalc(int year, int month, int day) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.clear();
		cal.set(year, month, day);
		return cal.getTime();
	}

	//인젝션 필터 (개발중)
	@SuppressWarnings("unchecked")
	public static boolean checkSqlKeyword(MoMap momap) {
		Iterator<String> iterator = momap.keySet().iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();
			String val = momap.getString(key);

			if (StringUtils.isNotEmpty(val)) {
				for (String keyword : SQL_KEY_WORDS) {
					if (val.toUpperCase().indexOf(keyword) != -1) {
						LOGGER.error("Occur Exception SQL_KEY_WORDS : {}", keyword);
						return false;
					}
				}
			}
		}

		return true;
	}

	public static void getErrorModelAndView(Exception e) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("Exception", e.toString());
		mv.addObject("msg", "에러");
		LOGGER.error("Occur Exception : {}", e.getLocalizedMessage());
		LOGGER.error("Occur Exception : {}", e.getMessage());
	}

	public static ModelAndView getErrorModelAndView(ModelAndView mv, Exception e) {
		mv.addObject("Exception", e.toString());
		mv.addObject("msg", "에러");
		mv.addObject("resultMsg", "에러");
		LOGGER.error("Occur Exception : {}", e.getLocalizedMessage());
		LOGGER.error("Occur Exception : {}", e.getMessage());
		return mv;
	}

	public static Model getErrorModel(Model model, Exception e) {
		model.addAttribute("Exception", e.toString());
		LOGGER.error("Occur Exception : {}", e.getLocalizedMessage());
		LOGGER.error("Occur Exception : {}", e.getMessage());
		return model;
	}

	public static ModelAndView getErrorModelAndView(ModelAndView mv, Exception e, String msg) {
		mv.addObject("Exception", e.toString());
		mv.addObject("resultMsg", msg);
		LOGGER.error("Occur Exception : {}", e.getLocalizedMessage());
		LOGGER.error("Occur Exception : {}", e.getMessage());
		return mv;
	}
}
