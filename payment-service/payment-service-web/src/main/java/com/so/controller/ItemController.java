package com.so.controller;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.so.dto.ItemRequest;
import com.so.service.PaymentService;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@RestController
public class ItemController {

	private PaymentService itemService;

	@Autowired
	private ItemController(PaymentService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public CompletableFuture<ResponseEntity> createItem(@RequestBody ItemRequest item) {
		return itemService.createItem(item.getItemCode(), item.getReservePrice()).thenApply(b -> new ResponseEntity(b.getEntityId(), HttpStatus.OK));
	}
}
