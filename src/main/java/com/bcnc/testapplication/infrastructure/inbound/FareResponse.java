package com.bcnc.testapplication.infrastructure.inbound;

import java.util.Date;

public class FareResponse {

    public long productId;
    public long brandId;
    public long fareId;
    public Date startDate;
    public Date endDate;
    public Price price;

    public static class Price {
        public Double value;
        public String currency;
    }

}
