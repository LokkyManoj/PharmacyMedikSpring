package com.chainsys.medik.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.medik.model.Payment;

public class PaymentMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("payment_id"));
        payment.setPaymentDate(rs.getDate("payment_date"));
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setUserId(rs.getInt("id"));
        payment.setProductId(rs.getInt("product_id"));
        return payment;
    }
}
