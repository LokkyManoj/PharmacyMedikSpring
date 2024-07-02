package com.chainsys.medik.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.chainsys.medik.model.Products;

public class ProductsMapper implements RowMapper<Products> {
    @Override
    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
        Products product = new Products();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductImage(rs.getBytes("product_image"));
        product.setProductQuantity(rs.getInt("product_quantity"));
        product.setProductPrice(rs.getInt("product_price"));
        product.setDescription(rs.getString("description"));
        product.setUses(rs.getString("uses"));
        product.setContains(rs.getString("contains"));
        product.setCategory(rs.getString("product_category"));
        product.setMfdDate(rs.getDate("mfd_date"));
        product.setExpDate(rs.getDate("exp_date"));
        product.setIsDeleted(rs.getInt("is_deleted"));
        return product;
    }
}