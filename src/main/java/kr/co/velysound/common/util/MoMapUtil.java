package kr.co.velysound.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.velysound.config.exception.RestApiException;
import kr.co.velysound.config.exception.error.CommonErrorCode;

import java.io.IOException;
import java.util.*;

public class MoMapUtil {
	/**
	 * Map객체에 MoMap Data를 합친다
	 *
	 * @param momap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> mergeMapAndMoMap(Map<String, Object> map, MoMap momap) {
		Iterator<String> iterator = momap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = momap.get(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * MoMap에 Map<String, Object> Data를 머지한다.
	 *
	 * @param momap
	 * @param map
	 * @return
	 */
	public static MoMap mergeMoMapAndMap(MoMap momap, Map<String, Object> map) {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = map.get(key);
			momap.put(key, value);
		}
		return momap;
	}

	/**
	 * List<HashMap> to List<MoMap>
	 *
	 * @param list
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<MoMap> listHashMapToMoMap(List<Map<String, Object>> list) {
		List<MoMap> returnList = new ArrayList<MoMap>();
		if (list == null) {
			return returnList;
		}
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> obj = (HashMap<String, Object>) list.get(i);
			MoMap mo = new MoMap();
			mo.putAll(obj);
			returnList.add(mo);
		}
		return returnList;
	}

	/**
	 * List<MoMap> to List<HashMap>
	 *
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> listMoMapToHashMap(List<MoMap> list) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MoMap obj = list.get(i);
			Map<String, Object> hash = new HashMap<String, Object>();
			hash.putAll((Map<String, Object>) obj);
			returnList.add(hash);
		}
		return returnList;
	}

	/**
	 * MoMap 반환
	 *
	 * @param jsonData
	 * @return
	 */
	public static MoMap PgJsonToMoMap(String jsonData) {
		ObjectMapper om = new ObjectMapper();
		MoMap m = new MoMap();
		try {
			m = om.readValue(jsonData, MoMap.class);
		} catch (JsonProcessingException e) {
			throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
		}

		return m;
	}
}
