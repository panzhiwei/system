package com.best.system.base.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class PageableArgumentByDataTablesResolver extends PageableArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) {
		Object result = super.resolveArgument(methodParameter, webRequest);
		if (result instanceof PageRequest) {
			try {
				// PageRequest page = (PageRequest) result;
				String pageNum = webRequest.getParameter("pageNum");
				String numPerPage = webRequest.getParameter("numPerPage");
				String orderField = webRequest.getParameter("orderField");
				String orderDirection = webRequest.getParameter("orderDirection");

				int iDisplayStart = Integer.parseInt(StringUtils.isNumeric(pageNum)?pageNum:"1")-1;//页面从1开始计数
				int iDisplayLength = Integer.parseInt(StringUtils.isNumeric(numPerPage)?numPerPage:"20");

				if(StringUtils.isBlank(orderField))
					return new PageRequest(iDisplayStart, iDisplayLength);
				Order order = new Order(StringUtils.equalsIgnoreCase("asc", orderDirection) ? Direction.ASC
						: Direction.DESC, orderField);

				return new PageRequest(iDisplayStart, iDisplayLength, new Sort(order));
			} catch (Exception ex) {
				ex.printStackTrace();
				return result;
			}
		}
		return result;
	}



}
