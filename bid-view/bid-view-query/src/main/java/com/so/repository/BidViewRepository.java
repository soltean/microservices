package com.so.repository;

import com.so.model.BidState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.so.view.BidView;

import java.util.List;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Repository
public interface BidViewRepository extends MongoRepository<BidView, String> {

    List<BidView> findByItemCode(String itemCode);

    BidView findByItemCodeAndState(String itemCode, BidState state);

}
