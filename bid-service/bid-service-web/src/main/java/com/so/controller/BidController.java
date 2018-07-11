package com.so.controller;

import com.so.domain.Bid;
import com.so.dto.BidRequest;
import com.so.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class BidController {

    private Logger log = Logger.getLogger(BidController.class.getName());
    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping(value = "/payBid")
    public ResponseEntity payBid(@RequestBody BidRequest bidRequest) {
        Bid bid = new Bid(bidRequest.getItemCode(), bidRequest.getAmount());
        try {
            return new ResponseEntity(bidService.payForBid(bid), HttpStatus.OK);
        } catch (Exception e) {
            log.severe(e + "");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/bid/{itemCode}")
    public ResponseEntity getBid(@PathVariable String itemCode) {
        try {
            return new ResponseEntity(bidService.find(itemCode), HttpStatus.OK);
        } catch (Exception e) {
            log.severe(e + "");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
