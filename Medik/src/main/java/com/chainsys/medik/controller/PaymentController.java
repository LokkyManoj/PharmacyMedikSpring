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
import com.chainsys.medik.model.Coupon;
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
                              @RequestParam("couponId") int couponId,

                              Model model, RedirectAttributes redirectAttributes) {
        Payment payment = new Payment();
        Coupon coupon=new Coupon();
        payment.setPaymentDate(paymentDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setUserId(userId);
        payment.setProductId(productId);
        coupon.setCouponId(couponId);
        System.out.println("CouponID"+coupon.getCouponId());

        try {
            boolean isPaymentSuccessful = medikDAO.payment(payment,coupon);
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
	                                @RequestParam(name = "couponId", required = false) Integer couponId,
	                                HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	        Payment payment = new Payment();
	        Coupon coupon=null;
	        payment.setPaymentDate(paymentDate);
	        payment.setPaymentMethod(paymentMethod);
	        payment.setAmount(amount);
	        payment.setUserId(userId);
	        payment.setProductId(productId);
	        
	        if (couponId != null && couponId > 0) {
	            coupon = new Coupon();
	            coupon.setCouponId(couponId);
	        }	        
	        try {
	            boolean isPaymentSuccessful = medikDAO.payment(payment,coupon);
	            if (isPaymentSuccessful) {
	            	System.out.println("Payment succesfull");
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
			/*
			 * session.removeAttribute("couponCode");
			 */	        boolean deleteSuccess = medikDAO.deleteCartItemsByUserId(userId);
			if (deleteSuccess) {
				System.out.println("Deleted cart items");
			    return "redirect:/pharmacyHome.jsp";
			} else {
			    model.addAttribute("message", "Failed to clear cart");
			    return "error";
			}
	    }

}
