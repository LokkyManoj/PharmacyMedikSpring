package com.chainsys.medik.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.Products;
import com.chainsys.medik.model.User;
import com.chainsys.medik.validator.Validation;

import jakarta.servlet.annotation.MultipartConfig;


@Controller
@MultipartConfig
public class AdminController {
	@Autowired
	MedikDAO medikDAO;

	   	@PostMapping("/addProduct")
	    public String addProduct(@RequestParam("product_id") int productId,
	                             @RequestParam("product_name") String productName,
	                             @RequestParam("product_image")MultipartFile file,
	                             @RequestParam("product_quantity") int productQuantity,
	                             @RequestParam("product_price") int productPrice,
	                             @RequestParam("description") String description,
	                             @RequestParam("uses") String uses,
	                             @RequestParam("contains") String contains,
	                             @RequestParam("product_category") String category,
	                             @RequestParam("mfd_date") Date mfdDate,
	                             @RequestParam("exp_date") Date expDate,
	                             Model model) throws SQLException {
		   
		   byte[] imageBytes = null;
	        if (!file.isEmpty()) 
	            try {
	                imageBytes = file.getBytes();
	            } catch (IOException e) {
	                e.printStackTrace();
	                return "error";
	            }

	        Products product = new Products();
	        User user=new User();
	        
	        product.setProductId(productId);
	        product.setProductName(productName);
	       
	            product.setProductImage(imageBytes);
	        
	       
	        product.setProductQuantity(productQuantity);
	        product.setProductPrice(productPrice);
	        product.setDescription(description);
	        product.setUses(uses);
	        product.setContains(contains);
	        product.setCategory(category);
	        product.setMfdDate(mfdDate);
	        product.setExpDate(expDate);
	        
	        Validation validator = new Validation();
	        if (!validator.validateProductForm(product, user, model)) {
	            return "addProducts.jsp";
	        }

	        if (medikDAO.addProduct(product)) {
			    model.addAttribute("message", "Product added successfully!");
			} else {
			    model.addAttribute("message", "Failed to add product.");
			}
	        return "addProducts.jsp" ;
	    }
	   
	   
	   	@GetMapping("/viewProducts")
	   	public String viewProducts(@RequestParam(value = "searchQuery", required = false) String searchQuery, Model model) {
	   	    List<Products> products;
	   	    if (searchQuery != null && !searchQuery.isEmpty()) {
	   	        products = medikDAO.searchProductsByName(searchQuery);
	   	    } else {
	   	        products = medikDAO.getAllProducts();
	   	    }
	   	    model.addAttribute("products", products);
	   	    return "viewProducts.jsp";
	   	}
	   
	   @GetMapping("/showUpadteForm")
	   public String showUpdateForm(@RequestParam("product_id") int productId, Model model) throws SQLException {
	        Products product = medikDAO.findProductById(productId);
	        model.addAttribute("product", product);
	        return "updateProducts.jsp";
	    }
	   
	   @PostMapping("updateProduct")
	   public String updateProduct(@RequestParam("product_id") int productId,
	                               @RequestParam("product_name") String productName,
	                               @RequestParam("product_quantity") int productQuantity,
	                               @RequestParam("product_price") int productPrice,
	                               @RequestParam("description") String description,
	                               @RequestParam("uses") String uses,
	                               @RequestParam("contains") String contains,
	                               @RequestParam("product_category") String category,
	                               @RequestParam("mfd_date") String mfdDateStr,
	                               @RequestParam("exp_date") String expDateStr, 
	                               Model model)  {
	       java.sql.Date mfdDate = java.sql.Date.valueOf(mfdDateStr);
	       java.sql.Date expDate = java.sql.Date.valueOf(expDateStr);
	       
	       Products product = new Products();
	       product.setProductId(productId);
	       product.setProductName(productName);
	       product.setProductQuantity(productQuantity);
	       product.setProductPrice(productPrice);
	       product.setDescription(description);
	       product.setUses(uses);
	       product.setContains(contains);
	       product.setCategory(category);
	       product.setMfdDate(mfdDate);
	       product.setExpDate(expDate);
	       
	       medikDAO.updateProducts(product);
	       
	       return "redirect:/viewProducts";
	   }
	   
		 @PostMapping("deleteProduct")
		    public String deleteProduct(@RequestParam("product_id") int productId, Model model) throws SQLException {
		        medikDAO.deleteProduct(productId);
		        List<Products> getDetails = medikDAO.getAllProducts();
		        model.addAttribute("product", getDetails);
		        return "redirect:/viewProducts";
		 }
		 

}

