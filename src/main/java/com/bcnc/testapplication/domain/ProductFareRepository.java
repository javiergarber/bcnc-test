package com.bcnc.testapplication.domain;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFareRepository {

    List<ProductFare> getFaresForProductByDateAndBrandId(Date applicationDate, long productId, long brandId);
}
