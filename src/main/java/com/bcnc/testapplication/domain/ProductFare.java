package com.bcnc.testapplication.domain;

import java.util.Date;

public class ProductFare {

    private final FareId fareId;
    private final BrandId brandId;
    private final Date startDate;
    private final Date endDate;
    private final ProductId productId;
    private final int priority;
    private final Price price;


    public FareId getFareId() {
        return fareId;
    }

    public BrandId getBrandId() {
        return brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getPriority() {
        return priority;
    }

    public Price getPrice() {
        return price;
    }


    public ProductFare(final FareId fareId, final BrandId brandId, final Date startDate, final Date endDate, final ProductId productId, final int priority,
        final Price price) {
        this.fareId = fareId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
    }

    public static class Price {

        private final double value;
        private final String currency;


        public double getValue() {
            return value;
        }

        public String getCurrency() {
            return currency;
        }


        public Price(final double value, final String currency) {
            this.value = value;
            this.currency = currency;
        }
    }

    public static class FareId {

        private final long fareId;

        public long getFareId() {
            return fareId;
        }

        public FareId(long fareId) {
            this.fareId = fareId;
        }
    }

    public static class BrandId {

        private final long brandId;

        public long getBrandId() {
            return brandId;
        }

        public BrandId(long brandId) {
            this.brandId = brandId;
        }
    }

    public static class ProductId {

        private final long productId;

        public long getProductId() {
            return productId;
        }

        public ProductId(long productId) {
            this.productId = productId;
        }
    }
}
