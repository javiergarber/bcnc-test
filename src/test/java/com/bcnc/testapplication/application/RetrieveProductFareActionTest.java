package com.bcnc.testapplication.application;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Sql(scripts = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RetrieveProductFareActionTest {

    @Autowired
    private RetrieveProductFareAction retrieveProductFareAction;

    @Test
    void test14June10am() {
        Date date = createDate(2020, 6, 14, 10, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = getFareDto(query);

        Assertions.assertEquals(1, fareDto.getFareId());
        Assertions.assertEquals(35.50, fareDto.getPrice().getValue());
    }

    @Test
    void test14June16pm() {
        Date date = createDate(2020, 6, 14, 16, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = getFareDto(query);

        Assertions.assertEquals(2, fareDto.getFareId());
        Assertions.assertEquals(25.45, fareDto.getPrice().getValue());
    }

    @Test
    void test14June21pm() {
        Date date = createDate(2020, 6, 14, 21, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = getFareDto(query);

        Assertions.assertEquals(1, fareDto.getFareId());
        Assertions.assertEquals(35.50, fareDto.getPrice().getValue());
    }

    @Test
    void test15June10am() {
        Date date = createDate(2020, 6, 15, 10, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = getFareDto(query);

        Assertions.assertEquals(3, fareDto.getFareId());
        Assertions.assertEquals(30.50, fareDto.getPrice().getValue());
    }

    @Test
    void test16June21pm() {
        Date date = createDate(2020, 6, 16, 21, 0, 0);
        RetrieveProductFareQuery query = new RetrieveProductFareQuery(date, 35455, RetrieveProductFareQuery.Brand.ZARA);
        FareDto fareDto = getFareDto(query);

        Assertions.assertEquals(4, fareDto.getFareId());
        Assertions.assertEquals(38.95, fareDto.getPrice().getValue());
    }

    private FareDto getFareDto(RetrieveProductFareQuery query) {
        Optional<FareDto> optionalFareDto = retrieveProductFareAction.execute(query);

        if (optionalFareDto.isEmpty()) {
            Assertions.fail("Fare not found");
        }
        return optionalFareDto.get();
    }

    private Date createDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        return calendar.getTime();
    }
}