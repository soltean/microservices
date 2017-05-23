package com.so.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.so.view.BidView;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Repository
public interface BidViewRepository extends MongoRepository<BidView, String> {

}
