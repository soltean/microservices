package com.so.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.so.model.ItemRequest;
import com.so.service.CacheService;

@Component
public class GetLatestItemsFallback implements ZuulFallbackProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CacheService cacheService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String getRoute() {
		return "item-view";
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
				logger.info("Item fallback activated");
				Collection<ItemRequest> latest = cacheService.getItemsFromCache();
				return new ByteArrayInputStream(objectMapper.writeValueAsString(latest).getBytes());
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
