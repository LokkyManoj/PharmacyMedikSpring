package com.chainsys.medik.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.CartItem;
import com.chainsys.medik.model.Coupon;
import com.chainsys.medik.model.Products;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller("customerController")
public class CustomerController {
	@Autowired
	MedikDAO medikDAO;
	
	@GetMapping("/searchMedicine")
    public String medicine(Model model) {
        String category = "Medicine";
        int isDeleted=0;
        List<Products> products = medikDAO.getProductsByCategory(category,isDeleted);
        model.addAttribute("products", products);
        return "medicine.jsp"; 
    }

	@GetMapping("/skinCareProducts")
    public String skinCare(Model model) {
        String category = "SkinCare";
        int isDeleted=0;
        List<Products> products = medikDAO.getProductsByCategory(category,isDeleted);
        model.addAttribute("products", products);
        return "skinCare.jsp"; 
    }
	
	@GetMapping("/fitnessSupplements")
    public String fitnessSupplements(Model model) {
        String category = "FitnessSupplements";
        int isDeleted=0;
        List<Products> products = medikDAO.getProductsByCategory(category,isDeleted);
        model.addAttribute("products", products);
        return "fitnessSupplements.jsp"; 
    }
	
	@GetMapping("/healthyProducts")
    public String healthyProducts(Model model) {
        String category = "HealthyFoodAndDrink";
        int isDeleted=0;
        List<Products> products = medikDAO.getProductsByCategory(category,isDeleted);
        model.addAttribute("products", products);
        return "healthyProducts.jsp"; 
    }
	
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("user_id") int userId,
	                        @RequestParam("product_id") int productId,
	                        @RequestParam("quantity") int quantity,
	                        Model model, HttpSession session) throws SQLException {
	    Products product = medikDAO.findProductById(productId);
//	    int remainingDays = getRemainingDays(product.getExpDate());
	    String productName=product.getProductName();
	    
	    if (medikDAO.addToCart(userId, productId, quantity)) {
	        model.addAttribute("message", "Product added to cart successfully!");
	    } else {
	        model.addAttribute("message", "Failed to add product to cart.");
	    }
	    int cartItemCount = medikDAO.getCartItemCount(userId);
	    session.setAttribute("cartItemCount", cartItemCount);

	    // Add remaining days to the session
		/*
		 * if (remainingDays <= 45) { session.setAttribute("expiryMessage",""
		 * +productName+" will expire in " + remainingDays + " days."); } else {
		 * session.removeAttribute("expiryMessage"); }
		 */

	    return "redirect:/searchMedicine";
	}

	public int getRemainingDays(java.sql.Date sqlExpDate) {
	    java.util.Date expDate = new java.util.Date(sqlExpDate.getTime());
	    
	    LocalDate expiryDate = expDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	    LocalDate currentDate = LocalDate.now();
	    return (int) ChronoUnit.DAYS.between(currentDate, expiryDate);
	}
	
	
	
	 @PostMapping("/viewCart")
	    public String viewCart(@RequestParam(value = "user_id",defaultValue = "0") int userId, HttpServletRequest request,HttpSession session,Model model) throws SQLException {
		 userId=(int) session.getAttribute("id");
		 System.out.println(userId);
	        List<CartItem> cartItems = medikDAO.getCartItemsByUserId(userId, request);
	        model.addAttribute("cartItems", cartItems);
	        List<Coupon> getDetails = medikDAO.viewCoupons();
	        model.addAttribute("coupons", getDetails);
	        return "viewCart.jsp"; 
	    }
	 
	 @PostMapping("/deleteCartItems")
	    public String deleteCartItemsByCartId(@RequestParam("user_id") int userId, @RequestParam("cart_id") int cartId, Model model,HttpServletRequest request) {
	        boolean isDeleted = medikDAO.deleteCartItemsByCartId(cartId);
	        if (isDeleted) {
	            model.addAttribute("message", "Cart items deleted successfully.");
	            List<CartItem> cartItems = medikDAO.getCartItemsByUserId(userId, request);
		        model.addAttribute("cartItems", cartItems);
	            return "viewCart"; 
	        } else {
	            model.addAttribute("message", "Failed to delete cart items.");
	            return "error"; 
	        } 
	 }
	 
//	 @PostMapping("/deleteCartItems")
//	 public String deleteCartItemsByCartId(@RequestParam("user_id") int userId, 
//	                                       @RequestParam("cart_id") int cartId, 
//	                                       Model model,
//	                                       HttpServletRequest request,
//	                                       HttpSession session) {
//	     boolean isDeleted = medikDAO.deleteCartItemsByCartId(cartId);
//	     if (isDeleted) {
//	         model.addAttribute("message", "Cart items deleted successfully.");
//	         
//	         // Remove expiry message if the deleted cart item caused it
//	         CartItem deletedItem = medikDAO.getCartItemByCartId(cartId); // Assuming this method retrieves the cart item
//	         if (deletedItem != null && deletedItem.isExpiryMessageEnabled()) {
//	             session.removeAttribute("expiryMessage");
//	         }
//	         
//	         List<CartItem> cartItems = medikDAO.getCartItemsByUserId(userId, request);
//	         model.addAttribute("cartItems", cartItems);
//	         
//	         return "viewCart"; 
//	     } else {
//	         model.addAttribute("message", "Failed to delete cart items.");
//	         return "error"; 
//	     } 
//	 }
	 
	 @PostMapping("/updateCartQuantity")
	 public String updateCartQuantity(@RequestParam("cartId") int cartId, 
	                                  @RequestParam("quantity") int quantity,
	                                  Model model, RedirectAttributes redirectAttributes) {
		 System.out.println("update cart");
	     boolean isUpdated = medikDAO.updateCartQuantity(cartId, quantity);
	     if (isUpdated) {
	         System.out.println("Quantity is Updated");
	         redirectAttributes.addFlashAttribute("message", "Cart quantity updated successfully!");
	     } else {
	         redirectAttributes.addFlashAttribute("message", "Failed to update cart quantity.");
	     }
	        return "viewCart"; 
	 }

	
	}
