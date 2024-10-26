package com.bcnc.testapplication.domain;

import java.util.Date;

public record ProductFare(FareId fareId,
                          BrandId brandId,
                          Date startDate,
                          Date endDate,
                          ProductId productId,
                          int priority,
                          Price price) {


    public record Price(double value, String currency) {


    }

    public record FareId(long id) {

    }

    public record BrandId(long id) {


    }

    public record ProductId(long id) {

    }
}
