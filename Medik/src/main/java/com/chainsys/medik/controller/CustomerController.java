package com.chainsys.medik.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.CartItem;
import com.chainsys.medik.model.Products;

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
                            Model model) throws SQLException {
        if (medikDAO.addToCart(userId, productId, quantity)) {
            model.addAttribute("message", "Product added to cart successfully!");
        } else {
            model.addAttribute("message", "Failed to add product to cart.");
        }

        return "redirect:/searchMedicine"; 
    }
	}
