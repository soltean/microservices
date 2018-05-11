package com.so.service;

import com.so.domain.Bid;
import com.so.repository.BidRepository;
import com.so.sagas.BidPaymentSagaData;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class BidService {

    private BidRepository bidRepository;

    @Autowired
    private SagaManager<BidPaymentSagaData> bidPaymentSagaSagaManager;

    @Autowired
    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Transactional
    public Bid payForBid(Bid bid) {
        BidPaymentSagaData data = new BidPaymentSagaData(bid.getId(), bid.getAmount());
        bidRepository.save(bid);
        bidPaymentSagaSagaManager.create(data, Bid.class, bid.getId());
        return bid;
    }

    public Optional<Bid> find(long bidId) {
        return bidRepository.findById(bidId);
    }
}
