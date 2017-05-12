package com.so.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.so.view.BidView;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Repository
public interface BidViewRepository extends CrudRepository<BidView, Long> {

}
