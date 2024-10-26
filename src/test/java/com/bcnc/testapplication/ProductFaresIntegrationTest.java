package com.bcnc.testapplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bcnc.testapplication.application.RetrieveProductFareAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class ProductFaresIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RetrieveProductFareAction retrieveProductFareAction;



    @Test
    void testGetFares() throws Exception {
        long productId = 35455;
        long brandId = 1;

        mockMvc.perform(get("/fares")
                .param("applicationDate", "2020-06-14T10:00:00Z")
                .param("productId", String.valueOf(productId))
                .param("brandId", String.valueOf(brandId))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(productId))
            .andExpect(jsonPath("$.brandId").value(brandId));
    }

    @Test
    void testGetFares_NotFound() throws Exception {
        long notExistentProductId = 2;
        long brandId = 1;

        mockMvc.perform(get("/fares")
                .param("applicationDate", "2020-06-14T10:00:00Z")
                .param("productId", String.valueOf(notExistentProductId))
                .param("brandId", String.valueOf(brandId))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

}
