package com.bcnc.testapplication.application;

import com.bcnc.testapplication.application.FareDto.Price;
import com.bcnc.testapplication.domain.ProductFare;
import com.bcnc.testapplication.domain.ProductFareRepository;
import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveProductFareAction {

    private final ProductFareRepository productFareRepository;

    @Autowired
    public RetrieveProductFareAction(ProductFareRepository productFareRepository) {
        this.productFareRepository = productFareRepository;
    }

    public Optional<FareDto> execute(RetrieveProductFareQuery query) {
        validateInput(query);

        List<ProductFare> fares = productFareRepository.getFaresForProductByDateAndBrandId(
            query.getApplicationDate(),
            query.getProductId(),
            query.getBrand().getId());

        if (fares.isEmpty()) {
            return Optional.empty();
        }

        if (fares.size() == 1) {
            ProductFare fare = fares.getFirst();
            return Optional.of(mapToDto(fare));
        }

        ProductFare maxPriorityFare = fares.stream().max(Comparator.comparing(ProductFare::getPriority)).get();
        return Optional.of(mapToDto(maxPriorityFare));
    }

    private FareDto mapToDto(ProductFare fare) {
        return new FareDto(fare.getProductId().getId(),
            fare.getBrandId().getId(),
            fare.getFareId().getId(),
            new FareDto.ApplicationDateRange(fare.getStartDate(), fare.getEndDate()),
            new Price(fare.getPrice().getValue(), fare.getPrice().getCurrency()));
    }

    private void validateInput(RetrieveProductFareQuery query) {
        if (query == null) {
            throw new InvalidParameterException("Query cannot be null");
        }
        if (query.getApplicationDate() == null) {
            throw new InvalidParameterException("Application date cannot be null");
        }
        if (query.getBrand() == null) {
            throw new InvalidParameterException("Brand Id cannot be null");
        }
    }

}
