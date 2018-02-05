import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Ognl工具类，主要是为了在ognl表达式访问静态方法时可以减少长长的类名称编写 Ognl访问静态方法的表达式为: @class@method(args)
 * 
 * 示例使用:
 * 
 * <pre>
 * 	&lt;if test="@Ognl@isNotEmpty(userId)">
 * 	and user_id = #{userId}
 * &lt;/if>
 * </pre>
 * 
 * @author YuLinlin
 * 
 */

public class Ognl {

	/**
	 * 可以用于判断String,Map,Collection,Array是否为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) throws IllegalArgumentException {
		if (o == null)
			return true;

		if (o instanceof String) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
			// throw new
			// IllegalArgumentException("Illegal argument type,must be : Map,Collection,Array,String. but was:"+o.getClass());
		}

		return false;
	}

	/**
	 * 可以用于判断 Map,Collection,String,Array是否不为空
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	public static boolean isNotBlank(Object o) {
		return !isBlank(o);
	}

	public static boolean isNumber(Object o) {
		if (o == null)
			return false;
		if (o instanceof Number) {
			return true;
		}
		if (o instanceof String) {
			String str = (String) o;
			if (str.length() == 0)
				return false;
			if (str.trim().length() == 0)
				return false;
			return StringUtils.isNumeric(str);
		}
		return false;
	}

	public static boolean isBlank(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			String str = (String) o;
			return isBlank(str);
		}
		return false;
	}

	public static boolean isBlank(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isStrEquals(String str,String str1) {
		if (str!=null && str1!=null && str1.equals(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isStrEquals(String str,String str1,String str2) {
		if ((str!=null && str1!=null && str1.equals(str)) || (str!=null && str2!=null && str2.equals(str))) {
			return true;
		}
		return false;
	}

}
