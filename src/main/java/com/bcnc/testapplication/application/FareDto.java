package com.bcnc.testapplication.application;

import java.util.Date;

public record FareDto(long productId, long brandId, long fareId, ApplicationDateRange applicationDateRange, Price price) {

    public record ApplicationDateRange(Date startDate, Date endDate) {

    }

    public record Price(double value, String currency) {

    }

}
