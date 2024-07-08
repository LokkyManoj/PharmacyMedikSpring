package com.chainsys.medik.controller;

import java.sql.Date;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.Payment;
import com.chainsys.medik.model.Products;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	MedikDAO medikDAO;
	
	@PostMapping("/makePayment")
    public String makePayment(@RequestParam("payment_date") Date paymentDate,
                              @RequestParam("payment_method") String paymentMethod,
                              @RequestParam("amount") double amount,
                              @RequestParam("user_id") int userId,
                              @RequestParam("product_id") int productId,
                              Model model, RedirectAttributes redirectAttributes) {
        Payment payment = new Payment();
        payment.setPaymentDate(paymentDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setUserId(userId);
        payment.setProductId(productId);

        try {
            boolean isPaymentSuccessful = medikDAO.payment(payment);
            if (isPaymentSuccessful) {
                redirectAttributes.addFlashAttribute("message", "Payment made successfully!");
                return "pharmacyHome.jsp"; 
            } else {
                model.addAttribute("message", "Payment failed. Please try again.");
                return "paymentForm"; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred during payment. Please try again.");
            return "paymentForm"; 
        }
    }
	
//	@PostMapping("/continueOrder")
//    public String continueOrder(HttpSession session, Model model) throws SQLException {
//        int userId = (int) session.getAttribute("id");
//        int productId = (int) session.getAttribute("product_id");
//        int quantity = (int) session.getAttribute("quantity");
//
//        Products product = medikDAO.findProductById(productId);
//		if (product != null) {
//		    int newQuantity = product.getProductQuantity() - quantity;
//		    if (newQuantity >= 0) {
//		        boolean updateSuccess = medikDAO.updateProductQuantity(productId, newQuantity);
//		        if (updateSuccess) {
//		            session.setAttribute("updateSuccess", true);
//		            System.out.println("update success");
//		            return "redirect:/lastPage.jsp";
//		        } else {
//		            model.addAttribute("message", "Failed to update product quantity");
//		            return "error";
//		        }
//		    } else {
//		        model.addAttribute("message", "Insufficient product quantity");
//		        return "error";
//		    }
//		} else {
//		    model.addAttribute("message", "Product not found");
//		    return "error";
//		}
//    }
	
	
	 @PostMapping("/continueOrder")
	    public String continueOrder(@RequestParam("payment_date") Date paymentDate,
	                                @RequestParam("payment_method") String paymentMethod,
	                                @RequestParam("amount") double amount,
	                                @RequestParam("user_id") int userId,
	                                @RequestParam("product_id") int productId,
	                                @RequestParam("quantity") int quantity,
	                                HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	        // Create and set payment details
	        Payment payment = new Payment();
	        payment.setPaymentDate(paymentDate);
	        payment.setPaymentMethod(paymentMethod);
	        payment.setAmount(amount);
	        payment.setUserId(userId);
	        payment.setProductId(productId);

	        try {
	            // Insert payment
	            boolean isPaymentSuccessful = medikDAO.payment(payment);
	            if (isPaymentSuccessful) {
	            	System.out.println("Payment succesfull");
	                // Update product quantity
	                Products product = medikDAO.findProductById(productId);
	                if (product != null) {
	                    int newQuantity = product.getProductQuantity() - quantity;
	                    if (newQuantity >= 0) {
	                        boolean updateSuccess = medikDAO.updateProductQuantity(productId, newQuantity);
	                        if (updateSuccess) {
	                        	System.out.println("updated quantity");
	                            session.setAttribute("updateSuccess", true);
	                            redirectAttributes.addFlashAttribute("message", "Payment made and product quantity updated successfully!");
	                            return "redirect:/lastPage.jsp";
	                        } else {
	                            model.addAttribute("message", "Failed to update product quantity");
	                            return "error";
	                        }
	                    } else {
	                        model.addAttribute("message", "Insufficient product quantity");
	                        return "error";
	                    }
	                } else {
	                    model.addAttribute("message", "Product not found");
	                    return "error";
	                }
	            } else {
	                model.addAttribute("message", "Payment failed. Please try again.");
	                return "paymentForm";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            model.addAttribute("message", "An error occurred during the process. Please try again.");
	            return "error";
	        }
	    }
	 

	    @GetMapping("/backtoHome")
	    public String home(HttpSession session, Model model){
	        int userId = (int) session.getAttribute("id");

	        boolean deleteSuccess = medikDAO.deleteCartItemsByUserId(userId);
			if (deleteSuccess) {
				System.out.println("Deleted cart items");
			    return "redirect:/pharmacyHome.jsp";
			} else {
			    model.addAttribute("message", "Failed to clear cart");
			    return "error";
			}
	    }

}
