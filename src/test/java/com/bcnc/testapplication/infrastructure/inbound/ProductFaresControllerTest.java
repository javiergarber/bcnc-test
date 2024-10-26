package com.bcnc.testapplication.infrastructure.inbound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bcnc.testapplication.application.FareDto;
import com.bcnc.testapplication.application.FareDto.ApplicationDateRange;
import com.bcnc.testapplication.application.FareDto.Price;
import com.bcnc.testapplication.application.RetrieveProductFareAction;
import com.bcnc.testapplication.application.RetrieveProductFareQuery;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ProductFaresControllerTest {

    @Mock
    private RetrieveProductFareAction retrieveProductFareAction;

    private ProductFaresController productFaresController;
    private AutoCloseable closeable;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
        productFaresController = new ProductFaresController(retrieveProductFareAction);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetFares_ReturnsFareResponse() {
        Date now = new Date();
        long productId = 35455;
        long brandId = 1;
        long fareId = 1;
        Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
        Date tomorrow = new Date(now.getTime() + 24 * 60 * 60 * 1000);

        FareDto fareDto = new FareDto(productId,
            brandId,
            fareId,
            new ApplicationDateRange(yesterday, tomorrow),
            new Price(15, "EUR"));

        when(retrieveProductFareAction.execute(any(RetrieveProductFareQuery.class))).thenReturn(Optional.of(fareDto));

        ResponseEntity<FareResponse> response = productFaresController.getFares(now, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        FareResponse fareResponse = response.getBody();
        assert fareResponse != null;
        assertEquals(productId, fareResponse.productId);
        assertEquals(brandId, fareResponse.brandId);
        assertEquals(1, fareResponse.fareId);
        assertEquals(15, fareResponse.price.value);
        assertEquals("EUR", fareResponse.price.currency);
    }

    @Test
    void testGetFares_NotFound() {
        Date applicationDate = new Date();
        long productId = 35455;
        long brandId = 1;

        when(retrieveProductFareAction.execute(any(RetrieveProductFareQuery.class))).thenReturn(Optional.empty());

        ResponseEntity<FareResponse> response = productFaresController.getFares(applicationDate, productId, brandId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}