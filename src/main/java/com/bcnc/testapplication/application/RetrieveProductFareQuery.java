package com.bcnc.testapplication.application;

import java.util.Date;

public record RetrieveProductFareQuery(Date applicationDate, long productId, Brand brand) {

    public enum Brand {
        ZARA(1);

        private final long id;

        Brand(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public static Brand fromId(long id) {
            for (Brand brand : Brand.values()) {
                if (brand.getId() == id) {
                    return brand;
                }
            }
            throw new IllegalArgumentException("Brand not found for id: " + id);
        }


    }
}

