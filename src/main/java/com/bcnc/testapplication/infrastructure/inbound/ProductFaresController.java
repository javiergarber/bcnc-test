package com.bcnc.testapplication.infrastructure.inbound;

import com.bcnc.testapplication.application.FareDto;
import com.bcnc.testapplication.application.RetrieveProductFareAction;
import com.bcnc.testapplication.application.RetrieveProductFareQuery;
import com.bcnc.testapplication.application.RetrieveProductFareQuery.Brand;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductFaresController {

    private final RetrieveProductFareAction retrieveProductFareAction;

    @Autowired
    public ProductFaresController(RetrieveProductFareAction retrieveProductFareAction) {
        this.retrieveProductFareAction = retrieveProductFareAction;
    }

    @GetMapping("/fares")
    public ResponseEntity<FareResponse> getFares(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date applicationDate,
        @RequestParam long productId,
        @RequestParam long brandId
    ) {
        Optional<FareDto> optionalFareDto = retrieveProductFareAction.execute(
            new RetrieveProductFareQuery(
                applicationDate,
                productId,
                Brand.fromId(brandId)));
        return optionalFareDto
            .map(this::ok)
            .orElseGet(this::emptyFareResponse);
    }

    private ResponseEntity<FareResponse> emptyFareResponse() {
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<FareResponse> ok(FareDto fareDto) {
        FareResponse fareResponse = new FareResponse();
        fareResponse.productId = fareDto.getProductId();
        fareResponse.brandId = fareDto.getBrandId();
        fareResponse.fareId = fareDto.getFareId();
        fareResponse.startDate = fareDto.getApplicationDateRange().getStartDate();
        fareResponse.endDate = fareDto.getApplicationDateRange().getEndDate();
        FareResponse.Price price = new FareResponse.Price();
        price.value = fareDto.getPrice().getValue();
        price.currency = fareDto.getPrice().getCurrency();
        fareResponse.price = price;
        return ResponseEntity.ok(fareResponse);
    }
}