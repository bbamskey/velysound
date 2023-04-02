package kr.co.velysound.common.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

public class MessageUtil {

	private static MessageSourceAccessor messageSourceAccessor = null;

	public static String getLocale() {
		String locale = LocaleContextHolder.getLocale().toString();
		String returnLocaleString = "ko";
		if (locale.equalsIgnoreCase("en")) {
			returnLocaleString = "en";
		}
		return returnLocaleString;
	}

	public static String getMessage(String key) {
		return messageSourceAccessor.getMessage(key, LocaleContextHolder.getLocale());
	}

	public static String getMessage(String key, Object... args) {
		return messageSourceAccessor.getMessage(key, args, Locale.getDefault());
	}

	public static String getMessage(Locale locale, String key, Object... args) {
		return messageSourceAccessor.getMessage(key, args, locale);
	}

	public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
		this.messageSourceAccessor = msAcc;
	}

}