package com.so.service;

import com.so.model.BidState;
import com.so.repository.BidViewRepository;
import com.so.view.BidView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Service
public class BidViewService {

    private BidViewRepository bidViewRepository;

    @Autowired
    private BidViewService(BidViewRepository bidViewRepository) {
        this.bidViewRepository = bidViewRepository;
    }

    public void addBid(BidView bid) {
        bidViewRepository.save(bid);
    }

    public List<BidView> getBidsForItem(String itemCode) {
        Example<BidView> itemCodeBidsExample = getBidViewByItemCodeExample(itemCode);
        return bidViewRepository.findAll(itemCodeBidsExample);
    }

    private void evaluateBids(String itemCode) {
        Example<BidView> itemCodeBidsExample = getBidViewByItemCodeExample(itemCode);
        List<BidView> bids = bidViewRepository.findAll(itemCodeBidsExample);
        bids.forEach(bidView -> bidView.setState(BidState.REJECT));
        BidView winningBid = bids.stream().max((o1, o2) -> o1.getAmount() - o2.getAmount()).get();
        winningBid.setState(BidState.WON);
        bidViewRepository.save(bids);
    }

    public BidView findWinningBid(String itemCode) {
        evaluateBids(itemCode);
        Example<BidView> winningBidExample = getWinningBidExample(itemCode);
        return bidViewRepository.findOne(winningBidExample);
    }

    private Example<BidView> getBidViewByItemCodeExample(String itemCode) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("itemCode", exact());
        return Example.of(new BidView(itemCode), matcher);
    }

    private Example<BidView> getWinningBidExample(String itemCode) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("itemCode", exact()).withMatcher("bidState", exact());
        return Example.of(new BidView(itemCode, BidState.WON), matcher);
    }

}
