package com.so.service;

import com.so.model.BidState;
import com.so.repository.BidViewRepository;
import com.so.view.BidView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Service
public class BidViewService {

    @Autowired
    private BidViewRepository bidViewRepository;

    public void addBid(BidView bid) {
        bidViewRepository.save(bid);
    }

    public void evaluateBids(List<BidView> bids) {
        bids.forEach(bidView -> bidView.setState(BidState.REJECT));
        BidView winningBid = bids.stream().max((o1, o2) -> o1.getAmount() - o2.getAmount()).get();
        winningBid.setState(BidState.WON);
        bidViewRepository.save(bids);
    }

}
