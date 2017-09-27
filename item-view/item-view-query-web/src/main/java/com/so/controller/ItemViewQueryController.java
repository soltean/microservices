package com.so.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.so.feign.ItemViewFeignClient;
import com.so.service.ItemViewService;
import com.so.view.ItemView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sergiu.oltean on 5/23/2017.
 */
@RestController
public class ItemViewQueryController implements ItemViewFeignClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ItemViewService itemViewService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ItemViewQueryController(ItemViewService itemViewService) {
        this.itemViewService = itemViewService;
    }

    public ResponseEntity<String> findAllItems() throws JsonProcessingException {
        List<ItemView> items = itemViewService.findAllItems();
        String itemsAsString = objectMapper.writeValueAsString(items);
        return new ResponseEntity<>(itemsAsString, HttpStatus.OK);
    }
}
