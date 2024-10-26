package com.bcnc.testapplication.application;

import java.util.Date;

public class RetrieveProductFareQuery {

    private final Date applicationDate;
    private final long productId;
    private final Brand brand;

    public RetrieveProductFareQuery(Date applicationDate, long productId, Brand brand) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brand = brand;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public long getProductId() {
        return productId;
    }

    public Brand getBrand() {
        return brand;
    }

    public enum Brand {
        ZARA(1);

        private final long id;

        Brand(long id) {
            this.id=id;
        }

        public long getId(){
            return id;
        }


    }
}

