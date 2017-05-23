package com.so.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import com.so.model.BidState;
import com.so.repository.BidViewRepository;
import com.so.view.BidView;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

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

	public void evaluateBids(String itemCode) {
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("itemCode", exact());
		Example<BidView> itemCodeBidsExample = Example.of(new BidView(itemCode), matcher);
		List<BidView> bids = bidViewRepository.findAll(itemCodeBidsExample);
		bids.forEach(bidView -> bidView.setState(BidState.REJECT));
		BidView winningBid = bids.stream().max((o1, o2) -> o1.getAmount() - o2.getAmount()).get();
		winningBid.setState(BidState.WON);
		bidViewRepository.save(bids);
	}

}
