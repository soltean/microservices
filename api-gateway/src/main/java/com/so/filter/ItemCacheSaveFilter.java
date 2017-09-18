package com.so.filter;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.so.service.CacheService;

@Component
public class ItemCacheSaveFilter extends ZuulFilter {

	private CacheService cacheService;

	@Autowired
	private ItemCacheSaveFilter(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Override
	public String filterType() {
		return "after";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		//make sure item save is called
		//TODO: check this
		return request.getMethod().equals("POST") && request.getRequestURI().contains("items");
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		//save to cache??
		return null;
	}
}
