package com.chainsys.medik.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.Coupon;

import jakarta.servlet.http.HttpSession;

@Controller
public class CouponController {
	@Autowired
	MedikDAO medikDAO;
	
	@PostMapping("addCoupon")
	public String addCoupon(@RequestParam("coupon_code")String couponCode,@RequestParam("discount")int discount,@RequestParam("validity")Date validity,@RequestParam("min_amount")int minAmount) {
		Coupon coupon=new Coupon();
		coupon.setCouponCode(couponCode);
		coupon.setDiscount(discount);
		coupon.setValidity(validity);
		coupon.setMinAmount(minAmount);
	    medikDAO.addCoupon(coupon);
	    
		
		return "adminPage.jsp";
		
	}
	
	 @PostMapping("click")
	    public String viewCoupons(HttpSession session,Model model)  {
	       
	        List<Coupon> getDetails = medikDAO.viewCoupons();
	        
	       
	        model.addAttribute("coupons", getDetails);


	        return "viewCoupons.jsp";
	    }
	 
	 @PostMapping("/applyCoupon")
	    public String applyCoupon(@ModelAttribute("couponCode") String couponCode, Model model,HttpSession session) {
	        Coupon coupon = medikDAO.getCouponByCode(couponCode);
	        if (coupon != null) {
		        session.setAttribute("couponCode",medikDAO.getCouponByCode(coupon.getCouponCode()) );

	            int currentTotal = (int) session.getAttribute("total"); 
	            
	            int minTotalAmount =coupon.getMinAmount();
	            
	            if (currentTotal >= minTotalAmount) {
	            	
	            
	            int discount = coupon.getDiscount();
	            int newTotal = currentTotal - (currentTotal * discount / 100);
	            System.out.println("CurrentTotal: "+currentTotal);
	            System.out.println("newTotal: "+newTotal);
	            	String totalDiscountedPrice =""+newTotal;
	            model.addAttribute("newTotal", totalDiscountedPrice);
	            session.setAttribute("newTotal1", totalDiscountedPrice);
	        } else {
	            model.addAttribute("couponError1", "Minimum total amount not met for this coupon");
	        }
	        } else {
	            model.addAttribute("couponError", "Invalid coupon code");
	        }
	        return "viewCart"; 
	    }

}
