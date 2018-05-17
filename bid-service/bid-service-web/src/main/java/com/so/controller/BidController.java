package com.so.controller;

import com.so.domain.Bid;
import com.so.dto.BidRequest;
import com.so.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController {

    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @RequestMapping(value = "/payBid", method = RequestMethod.POST)
    public ResponseEntity payBid(@RequestBody BidRequest bidRequest) {
        Bid bid = new Bid(bidRequest.getItemCode(), bidRequest.getAmount());
        try {
            return new ResponseEntity(bidService.payForBid(bid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
