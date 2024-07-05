package com.chainsys.medik.model;

import java.sql.Date;

public class Orders {
    private int orderId;
    private int productId;
    private Date orderDate;
    private int quantity;
    private String status;
    private Date expectedDeliveryDate;
    private int userId;
    private String address;

    public Orders() {
    }

    public Orders(int productId, Date orderDate, int quantity, String status, Date expectedDeliveryDate, int userId, String address) {
        this.productId = productId;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.status = status;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.userId = userId;
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", productId=" + productId + ", orderDate=" + orderDate + ", quantity="
				+ quantity + ", status=" + status + ", expectedDeliveryDate=" + expectedDeliveryDate + ", userId="
				+ userId + ", address=" + address + "]";
	}

}

