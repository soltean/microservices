package com.so.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.so.model.BidRequest;
import com.so.model.ItemRequest;

@Service
public class CacheService {

	private final Map<String, ItemRequest> itemCache = new ConcurrentHashMap<>();
	private final Map<String, Set<BidRequest>> bidCache = new ConcurrentHashMap<>();

	@HystrixCommand
	@CacheResult
	public ItemRequest createItem(@CacheKey ItemRequest itemRequest) {
		return new ItemRequest(itemRequest.getItemCode(), itemRequest.getReservePrice());
	}

	public ItemRequest saveItemToCache(String key, ItemRequest itemRequest) {
		return itemCache.putIfAbsent(key, itemRequest);
	}

	public Set<BidRequest> saveBidToCache(String key, BidRequest bidRequest) {
		Set<BidRequest> existingRequests = bidCache.computeIfAbsent(key, bids -> new HashSet<>());
		existingRequests.add(bidRequest);
		return bidCache.putIfAbsent(key, existingRequests);
	}

	public Collection<ItemRequest> getItemsFromCache() {
		return itemCache.values();
	}

	public Set<BidRequest> getBidsFromCache(String key) {
		return bidCache.get(key);
	}

}
