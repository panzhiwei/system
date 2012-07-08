package org.springside.examples.miniweb.web.Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;

public class QueryParamUtils {
	public static Map<String, Object> getParametersStartingWith(NativeWebRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		// Enumeration paramNames = request.getp
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		Iterator<String> it = request.getParameterNames();
		while (it.hasNext()) {
			String paramName = it.next();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}
}
