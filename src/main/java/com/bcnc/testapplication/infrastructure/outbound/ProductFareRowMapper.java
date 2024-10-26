package com.bcnc.testapplication.infrastructure.outbound;

import com.bcnc.testapplication.domain.ProductFare;
import com.bcnc.testapplication.domain.ProductFare.BrandId;
import com.bcnc.testapplication.domain.ProductFare.FareId;
import com.bcnc.testapplication.domain.ProductFare.Price;
import com.bcnc.testapplication.domain.ProductFare.ProductId;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductFareRowMapper implements RowMapper<ProductFare> {

    @Override
    public ProductFare mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductFare(new FareId(rs.getLong("price_list")),
            new BrandId(rs.getLong("brand_id")),
            rs.getDate("start_date"),
            rs.getDate("end_date"),
            new ProductId(rs.getLong("product_id")),
            rs.getInt("priority"),
            new Price(rs.getDouble("price"), rs.getString("curr")));
    }

}
