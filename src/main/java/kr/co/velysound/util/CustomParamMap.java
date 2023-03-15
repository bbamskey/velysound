package kr.co.velysound.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
@SuppressWarnings("unchecked")
public class CustomParamMap extends HashMap<Object, Object> {
	public Object put(Object key, Object value) {
		// log.info("####### key : " + key);
		// log.info("####### value : " + value);

		String k = (String) key;
		if (k.equals("paging") && value != null) {
			HashMap<String, Integer> v = (HashMap<String, Integer>) value;
			int page = v.getOrDefault("page", -1);
			int size = v.getOrDefault("size", 10);
			int start = v.getOrDefault("start", -1);

			if (page == -1 && start == -1) {
				start = 0;
			} else if (page >= 1 && start == -1) {
				start = (page - 1) * size;
			} else if (start <= 0) {
				start = 0;
			} else {
				start--;
			}

			v.put("start", start);
			value = v;

		}
		log.info("####### key : " + key + " ## value : " + value);
		return super.put(key, value);
	}
}
