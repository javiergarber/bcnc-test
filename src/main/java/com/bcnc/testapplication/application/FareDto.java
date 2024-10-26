package com.bcnc.testapplication.application;

import java.util.Date;

public class FareDto {

    private final long productId;
    private final long brandId;
    private final long fareId;
    private final ApplicationDateRange applicationDateRange;
    private final Price price;


    public FareDto(long productId, long brandId, long fareId, ApplicationDateRange applicationDateRange, Price price) {
        this.productId = productId;
        this.brandId = brandId;
        this.fareId = fareId;
        this.applicationDateRange = applicationDateRange;
        this.price = price;
    }


    public long getProductId() {
        return productId;
    }

    public long getBrandId() {
        return brandId;
    }

    public long getFareId() {
        return fareId;
    }

    public ApplicationDateRange getApplicationDateRange() {
        return applicationDateRange;
    }

    public Price getPrice() {
        return price;
    }

    public static class ApplicationDateRange {

        private final Date startDate;
        private final Date endDate;

        public ApplicationDateRange(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

    }

    public static class Price {

        private final double value;
        private final String currency;

        public Price(double value, String currency) {
            this.value = value;
            this.currency = currency;
        }

        public double getValue() {
            return value;
        }

        public String getCurrency() {
            return currency;
        }

    }

}
