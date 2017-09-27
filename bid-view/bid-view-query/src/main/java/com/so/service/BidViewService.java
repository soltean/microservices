package com.so.service;

import com.so.model.BidState;
import com.so.repository.BidViewRepository;
import com.so.view.BidView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Service
public class BidViewService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private BidViewRepository bidViewRepository;

    @Autowired
    private BidViewService(BidViewRepository bidViewRepository) {
        this.bidViewRepository = bidViewRepository;
    }

    public void addBid(BidView bid) {
        bidViewRepository.save(bid);
    }

    public List<BidView> getBidsForItem(String itemCode) {
        return bidViewRepository.findByItemCode(itemCode);
    }

    private void evaluateBids(String itemCode) {
        List<BidView> bids = bidViewRepository.findByItemCode(itemCode);
        bids.forEach(bidView -> bidView.setState(BidState.REJECT));
        BidView winningBid = bids.stream().max((o1, o2) -> o1.getAmount() - o2.getAmount()).get();
        winningBid.setState(BidState.WON);
        bidViewRepository.save(bids);
    }

    public BidView findWinningBid(String itemCode) {
        evaluateBids(itemCode);
        return bidViewRepository.findByItemCodeAndState(itemCode, BidState.WON);
    }
}
