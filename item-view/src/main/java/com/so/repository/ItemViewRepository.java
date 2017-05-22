package com.so.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.so.view.ItemView;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Repository
public interface ItemViewRepository extends MongoRepository<ItemView, String> {

}
