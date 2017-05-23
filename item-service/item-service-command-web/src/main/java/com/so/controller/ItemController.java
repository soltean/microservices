package com.so.controller;

import so.dto.ItemDto;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.so.dto.ItemDto;
import com.so.service.ItemService;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public CompletableFuture<ResponseEntity> createItem(@RequestBody ItemDto item) {
		return itemService.createItem(item.getItemCode(), item.getReservePrice()).thenApply(b -> new ResponseEntity(b.getEntityId(), HttpStatus.OK));
	}
}
