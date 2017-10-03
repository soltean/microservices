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
import com.so.model.BidRequest;
import com.so.service.CacheService;

@Component
public class BidCacheSaveFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private CacheService cacheService;

	@Autowired
	private BidCacheSaveFilter(CacheService cacheService) {
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
		return request.getMethod().equals("POST") && request.getRequestURI().contains("bids");
	}

	@Override
	public Object run() {
		logger.info("Caching bids");
		RequestContext ctx = RequestContext.getCurrentContext();
		int responseStatus = ctx.getResponse().getStatus();
		if (responseStatus == 200) {
			try {
				ServletInputStream request = ctx.getRequest().getInputStream();
				String req = StreamUtils.copyToString(request, Charset.defaultCharset());
				BidRequest bidRequest = new ObjectMapper().readValue(req, BidRequest.class);
				cacheService.saveBidToCache(bidRequest.getItemCode(), bidRequest);
			} catch (IOException e) {
				logger.error("An error occurred", e);
			}
		}
		return null;
	}
}
