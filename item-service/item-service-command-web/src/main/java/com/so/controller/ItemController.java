package com.so.controller;

import com.so.feign.ItemCommandFeignClient;
import com.so.model.ItemRequest;
import com.so.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@RestController
public class ItemController implements ItemCommandFeignClient {

    private ItemService itemService;

    @Autowired
    private ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    public ResponseEntity<String> createItem(@RequestBody ItemRequest item) throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity> result = itemService.createItem(item.getItemCode(), item.getReservePrice()).thenApply(b -> new ResponseEntity(b.getEntityId(), HttpStatus.OK));
        return result.get();
    }
}
