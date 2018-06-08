package com.so.repository;

import com.so.domain.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {
}
