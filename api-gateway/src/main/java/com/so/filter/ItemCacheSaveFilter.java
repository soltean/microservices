package com.so.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
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
		//make sure item save is called
		//TODO: check this
		return request.getMethod().equals("POST") && request.getRequestURI().contains("items");
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		InputStream content = ctx.getResponseDataStream();
		ByteArrayOutputStream copy = new ByteArrayOutputStream();
		try {
			StreamUtils.copy(content, copy);
			content.close();
			copy.close();
			ctx.setResponseDataStream(new ByteArrayInputStream(copy.toByteArray()));

			String req = StreamUtils.copyToString(content, Charset.defaultCharset());
			ItemRequest itemRequest = new ObjectMapper().readValue(req, ItemRequest.class);
			cacheService.saveItemToCache(itemRequest.getItemCode(), itemRequest);
		} catch (IOException e) {
			//print
		}
		return null;
	}
}
