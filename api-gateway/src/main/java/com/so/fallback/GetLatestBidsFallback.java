package com.so.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.so.model.BidRequest;
import com.so.service.CacheService;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetLatestBidsFallback implements ZuulFallbackProvider {

	@Autowired
	private CacheService cacheService;

	private ObjectMapper objectMapper = new ObjectMapper();

	private String itemCode;

	public GetLatestBidsFallback() {
	}

	public GetLatestBidsFallback(String itemCode) {
		this.itemCode = itemCode;
	}

	// Might be confusing: it's the serviceId property and not the route
	@Override
	public String getRoute() {
		return "getItems";
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
