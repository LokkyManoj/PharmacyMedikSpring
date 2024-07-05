package com.chainsys.medik.controller;

import java.sql.SQLException;
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
import com.chainsys.medik.model.Products;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
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
                            Model model,HttpSession session) throws SQLException {
        if (medikDAO.addToCart(userId, productId, quantity)) {
            model.addAttribute("message", "Product added to cart successfully!");
        } else {
            model.addAttribute("message", "Failed to add product to cart.");
        }
        int cartItemCount = medikDAO.getCartItemCount(userId);
        session.setAttribute("cartItemCount",cartItemCount);

        return "redirect:/searchMedicine"; 
    }
	
	 @PostMapping("/viewCart")
	    public String viewCart(@RequestParam(value = "user_id",defaultValue = "0") int userId, HttpServletRequest request,HttpSession session,Model model) throws SQLException {
		 System.out.println("eeeeeee");
		 userId=(int) session.getAttribute("id");
	        List<CartItem> cartItems = medikDAO.getCartItemsByUserId(userId, request);
	        model.addAttribute("cartItems", cartItems);
	        return "viewCart.jsp"; 
	    }
	 
	 @PostMapping("/deleteCartItems")
	    public String deleteCartItemsByUserId(@RequestParam("user_id") int userId, @RequestParam("cart_id") int cartId, Model model,HttpServletRequest request) {
	        boolean isDeleted = medikDAO.deleteCartItemsByUserId(cartId);
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
	     
	     //List<CartItem> cartItems = medikDAO.getCartItemsByUserId(userId, request);
	        //model.addAttribute("cartItems", cartItems);
	        return "viewCart"; 
	 }

	
	}
