package com.so.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.context.RequestContext;
import com.so.model.BidRequest;
import com.so.service.CacheService;

@Component
public class GetLatestBidsFallback implements ZuulFallbackProvider {

	@Autowired
	private CacheService cacheService;

	private ObjectMapper objectMapper = new ObjectMapper();

	public GetLatestBidsFallback(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Override
	public String getRoute() {
		return "bid-view";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {

		return new ClientHttpResponse() {

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
				String itemCodeReq = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
				String itemCode = itemCodeReq.split("/")[1];
				Collection<BidRequest> latestBidsForItem = cacheService.getBidsFromCache(itemCode);
				return new ByteArrayInputStream(objectMapper.writeValueAsString(latestBidsForItem).getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}
}
