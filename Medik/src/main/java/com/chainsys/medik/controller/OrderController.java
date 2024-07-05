package com.chainsys.medik.controller;


import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.Orders;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	MedikDAO medikDAO;
	
	@GetMapping("/placeOrder")
    public String showOrderForm(Model model, HttpSession session) {
        Orders order = new Orders();
      order.setOrderDate(Date.valueOf(LocalDate.now()));
      System.out.println("Order Date:"+order.getOrderDate());
    session.setAttribute("orderdate", order.getOrderDate());      
        order.setExpectedDeliveryDate(Date.valueOf(LocalDate.now().plusDays(3)));
        session.setAttribute("expectedDeliveryDate", order.getExpectedDeliveryDate());
        System.out.println("Expected Delivery Date:"+order.getExpectedDeliveryDate());
        int productId = (int) session.getAttribute("product_id");
        int userId = (int) session.getAttribute("id");
        int quantity = (int) session.getAttribute("quantity");
        int total = (int) session.getAttribute("total");
        System.out.println("userID"+userId);
        System.out.println("quantity"+quantity);
        System.out.println("total"+total);
        System.out.println("productid :"+productId);

        return "order.jsp";
    }

	@PostMapping("/order")
	public String placeOrder(@RequestParam("total") int total,
	                         @RequestParam("productId") int productId,
	                         @RequestParam("userId") int userId,
	                         @RequestParam("quantity") int quantity,
	                         @RequestParam("status") String status,
	                         @RequestParam("orderDate") Date orderDate,
	                         @RequestParam("expectedDeliveryDate") Date expectedDeliveryDate,
	                         @RequestParam("address") String address,
	                         RedirectAttributes redirectAttributes) {
	    Orders order = new Orders();
	    order.setProductId(productId);
	    order.setUserId(userId);
	    order.setQuantity(quantity);
	    order.setStatus(status);
	    order.setOrderDate(orderDate);
	    order.setExpectedDeliveryDate(expectedDeliveryDate);
	    order.setAddress(address);

	    boolean isOrderPlaced = medikDAO.placeOrder(order);

	    if (isOrderPlaced) {
	        redirectAttributes.addFlashAttribute("message", "Order placed successfully!");
	        return "pharmacyHome.jsp"; 
	    } else {
	        redirectAttributes.addFlashAttribute("error", "Failed to place order. Please try again.");
	        return "redirect:/orderFailure";
	    }
	}
}
