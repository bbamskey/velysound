package kr.co.velysound.common.util;

import org.apache.commons.collections.map.ListOrderedMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoMap extends ListOrderedMap {

	/**
	 * serail
	 */
	private static final long serialVersionUID = 1706475858162491211L;

	/**
	 * CamelCase 형식으로 지정
	 */
	public Object put(Object key, Object value) {
		if (key.toString().contains("_")) {
			return super.put(CamelCaseUtil.convertToCalmelCase(key.toString()), value);
		} else {
			return super.put(key, value);
		}
	}

	/**
	 * camelcase 형식을 준수안할 경우
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Object putKey(Object key, Object value) {
		return super.put((String) key, value);
	}

	/**
	 * object에 담겨있는 string 데이타를 추출한다.
	 *
	 * @param key
	 * @return
	 */
	public String getString(Object key) {
		Object obj = super.get(key);

		if (obj != null) {
			if (obj instanceof String) {
				return (String) obj;
			} else if (obj instanceof BigDecimal) {
				return ((BigDecimal) obj).stripTrailingZeros().toPlainString();
			} else if (obj instanceof Integer) {
				return String.valueOf((Integer) obj);
			} else if (obj instanceof Long) {
				return String.valueOf((Long) obj);
			} else if (obj instanceof Float) {
				return String.valueOf((Float) obj);
			} else if (obj instanceof Double) {
				if (!Double.isNaN((Double) obj) && !Double.isInfinite((Double) obj)) {
					return new BigDecimal((Double) obj).stripTrailingZeros().toPlainString();
				} else {
					return String.valueOf(obj);
				}
			} else {
				return String.valueOf(obj);
			}
		}

		return "";
	}

	/**
	 * object에 담겨있는 int 데이타를 추출한다.
	 *
	 * @param key
	 * @return
	 */
	public int getInt(Object key) {
		Object obj = super.get(key);

		if (obj != null) {
			if (obj instanceof BigDecimal) {
				return ((Number) obj).intValue();
			} else if (obj instanceof Integer) {
				return (Integer) obj;
			} else if (obj instanceof Long) {
				return ((Long) obj).intValue();
			} else if (obj instanceof Float) {
				return ((Float) obj).intValue();
			} else if (obj instanceof Double) {
				return ((Double) obj).intValue();
			} else if (obj instanceof String) {
				return Integer.parseInt((String) obj);
			}
		}

		return 0;
	}

	/**
	 * 더블데이터 추출
	 *
	 * @param key
	 * @return
	 */
	public double getDouble(Object key) {
		Object obj = super.get(key);
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				return ((Number) obj).doubleValue();
			} else if (obj instanceof Double) {
				return (Double) obj;
			} else if (obj instanceof Long) {
				return ((Long) obj).doubleValue();
			} else if (obj instanceof Float) {
				return ((Float) obj).doubleValue();
			} else if (obj instanceof String) {
				return Double.parseDouble((String) obj);
			}
		}

		return 0;
	}

	/**
	 * 문자열배열형태를 List object 담아 전송한다.
	 *
	 * @return : List<String>
	 * @MethodName : getStringList
	 */
	public List<String> getStringList(Object key) {
		Object obj = super.get(key);
		List<String> rList = new ArrayList<String>();

		if (obj != null) {
			if (obj instanceof String[]) {
				String[] aValue = (String[]) obj;
				for (int i = 0; i < aValue.length; i++) {
					rList.add(aValue[i]);
				}
			} else if (obj instanceof List) {
				@SuppressWarnings("unchecked")
				ArrayList<String> aValueList = (ArrayList<String>) obj;
				for (String aValue : aValueList) {
					rList.add(aValue);
				}
			} else if (obj instanceof String) {
				rList.add((String) obj);
			}
		}

		return rList;
	}

	/**
	 * 페이징처리를 위한 함수
	 */
	@SuppressWarnings("unchecked")
	public void setPaging() {
		HashMap<String, Object> paging = (HashMap<String, Object>) this.get("paging");

		if (paging == null) return;

		int size = Integer.parseInt(String.valueOf(paging.get("size")));
		int page = Integer.parseInt(String.valueOf(paging.get("page")));
		String next = String.valueOf(paging.get("next"));

		paging.put("size", size == 0 ? 10000 : size);
		paging.put("page", page);
		paging.put("start", size * (page - 1));
		paging.put("next", next);

		this.put("paging", paging);
	}
}
