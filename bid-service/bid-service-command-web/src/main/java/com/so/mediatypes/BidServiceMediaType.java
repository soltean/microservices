package com.so.mediatypes;

import org.springframework.http.MediaType;

public class BidServiceMediaType {
    public static final String BID_SERVICE_BID_VALUE = "application/vnd.bid-service.bids+json";
    public static final MediaType BID_SERVICE_BIDS = MediaType.valueOf(BID_SERVICE_BID_VALUE);
}
