package com.chainsys.medik.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.mapper.ProductsMapper;
import com.chainsys.medik.model.Products;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	@Autowired
	MedikDAO medikDAO;

	   @PostMapping("/addProduct")
	    public String addProduct(@RequestParam("product_id") int productId,
	                             @RequestParam("product_name") String productName,
	                             @RequestParam("product_image") byte[] productImage,
	                             @RequestParam("product_quantity") int productQuantity,
	                             @RequestParam("product_price") int productPrice,
	                             @RequestParam("description") String description,
	                             @RequestParam("uses") String uses,
	                             @RequestParam("contains") String contains,
	                             @RequestParam("product_category") String category,
	                             @RequestParam("mfd_date") Date mfdDate,
	                             @RequestParam("exp_date") Date expDate,
	                             Model model) throws SQLException {

	        Products product = new Products();
	        product.setProductId(productId);
	        product.setProductName(productName);
	       
	            product.setProductImage(productImage);
	        
	       
	        product.setProductQuantity(productQuantity);
	        product.setProductPrice(productPrice);
	        product.setDescription(description);
	        product.setUses(uses);
	        product.setContains(contains);
	        product.setCategory(category);
	        product.setMfdDate(mfdDate);
	        product.setExpDate(expDate);

	        if (medikDAO.addProduct(product)) {
			    model.addAttribute("message", "Product added successfully!");
			} else {
			    model.addAttribute("message", "Failed to add product.");
			}
	        return "addProducts.jsp";
	    }
}

