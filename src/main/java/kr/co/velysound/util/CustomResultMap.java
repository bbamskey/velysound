package kr.co.velysound.util;

import com.google.common.base.CaseFormat;

import java.util.HashMap;

public class CustomResultMap extends HashMap<Object, Object> {
	public Object put(Object key, Object value) {
		return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, (String) key), value);
	}
}
