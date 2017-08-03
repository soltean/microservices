package com.so.events.item;

import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * Created by sergiu.oltean on 5/9/2017.
 */
@EventEntity(entity = "com.so.aggregate.ItemAggregate")
public interface ItemEvent extends Event {

}
