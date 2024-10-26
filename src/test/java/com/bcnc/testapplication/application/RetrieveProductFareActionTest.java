package com.bcnc.testapplication.application;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
class RetrieveProductFareActionTest {

    private RetrieveProductFareAction retrieveProductFareAction;

    @BeforeEach
    void setUp() {
        retrieveProductFareAction = new RetrieveProductFareAction();
    }

    @Test
    void test14June10am() {
        Date date = createDate(2020, 6, 14, 10, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = retrieveProductFareAction.execute(query);

        Assertions.assertEquals(1, fareDto.getFareId());
        Assertions.assertEquals(35.50, fareDto.getPrice().getValue());
    }

    @Test
    void test14June16pm() {
        Date date = createDate(2020, 6, 14, 16, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = retrieveProductFareAction.execute(query);

        Assertions.assertEquals(2, fareDto.getFareId());
        Assertions.assertEquals(25.45, fareDto.getPrice().getValue());
    }

    @Test
    void test14June21pm() {
        Date date = createDate(2020, 6, 14, 21, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = retrieveProductFareAction.execute(query);

        Assertions.assertEquals(1, fareDto.getFareId());
        Assertions.assertEquals(35.50, fareDto.getPrice().getValue());
    }

    @Test
    void test15June10am() {
        Date date = createDate(2020, 6, 15, 10, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = retrieveProductFareAction.execute(query);

        Assertions.assertEquals(3, fareDto.getFareId());
        Assertions.assertEquals(30.50, fareDto.getPrice().getValue());
    }

    @Test
    void test16June21pm() {
        Date date = createDate(2020, 6, 16, 21, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = retrieveProductFareAction.execute(query);

        Assertions.assertEquals(4, fareDto.getFareId());
        Assertions.assertEquals(38.95, fareDto.getPrice().getValue());
    }

    private Date createDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        return calendar.getTime();
    }
}