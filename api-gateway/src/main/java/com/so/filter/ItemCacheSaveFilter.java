package com.so.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.so.model.ItemRequest;
import com.so.service.CacheService;

@Component
public class ItemCacheSaveFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private CacheService cacheService;

	@Autowired
	private ItemCacheSaveFilter(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		return request.getMethod().equals("POST") && request.getRequestURI().contains("items");
	}

	@Override
	public Object run() {
		logger.info("Caching items");
		RequestContext ctx = RequestContext.getCurrentContext();
		int responseStatus = ctx.getResponse().getStatus();
		if (responseStatus == 200) {
			try {
				ServletInputStream request = ctx.getRequest().getInputStream();
				String req = StreamUtils.copyToString(request, Charset.defaultCharset());
				ItemRequest itemRequest = new ObjectMapper().readValue(req, ItemRequest.class);
				cacheService.saveItemToCache(itemRequest.getItemCode(), itemRequest);
			} catch (IOException e) {
				logger.error("An error occurred", e);
			}
		}

		return null;
	}
}
