package com.bcnc.testapplication.infrastructure.outbound;

import com.bcnc.testapplication.domain.ProductFare;
import com.bcnc.testapplication.domain.ProductFareRepository;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductFareRepositoryAdapter implements ProductFareRepository {


    private final DataSource dataSource;

    @Autowired
    public ProductFareRepositoryAdapter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<ProductFare> getFaresForProductByDateAndBrandId(Date applicationDate, long productId, long brandId) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("applicationDate", applicationDate);
        parameters.addValue("productId", productId);
        parameters.addValue("id", brandId);
        return namedParameterJdbcTemplate.
            query("SELECT * FROM PRICES WHERE start_date<= :applicationDate AND :applicationDate <= END_DATE AND PRODUCT_ID = :productId AND BRAND_ID = :id",
                parameters,
                new ProductFareRowMapper());

    }
}
