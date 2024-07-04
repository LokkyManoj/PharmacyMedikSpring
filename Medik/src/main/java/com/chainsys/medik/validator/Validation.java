package com.chainsys.medik.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;

import com.chainsys.medik.model.Products;
import com.chainsys.medik.model.User;

public class Validation {
	public boolean validateProductCategory(String productCategory) {
        String regex = "[A-Za-z\\s]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(productCategory);
        return matcher.matches();
    }

    public boolean validateProductQuantity(int productQuantity) {
        return productQuantity > 0;
    }

    public boolean validateProductPrice(int productPrice) {
        return productPrice > 0;
    }
    
    public boolean validateProductId(int productId) {
    	return productId>0;
    }
    
    public boolean validateUserName(String userName) {
    	String regex = "[A-Za-z\\s]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
    
    public boolean validateMobileNo(String mobileNo) {
    	 String regex = "^\\d{10}$";
    	 Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(mobileNo);

         return matcher.matches();
    }
    
    public boolean validateEmail(String email) {
        
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
       Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean validateProductForm(Products product,User user, Model model) {
        boolean isValid = true;

        if (!validateProductCategory(product.getCategory())) {
        	System.out.println("Error: Product Category is invalid. Only letters and spaces are allowed.");
            isValid = false;
        }

        if (!validateProductQuantity(product.getProductQuantity())) {
        	System.out.println("Error: Product Quantity must be greater than 0.");
            isValid = false;
        }

        if (!validateProductPrice(product.getProductPrice())) {
        	System.out.println("Error: Product Price must be greater than 0.");
            isValid = false;
        }
        
        if(!validateProductPrice(product.getProductId())) {
        	System.out.println("Error: Product Id must be greater than 0.");
            isValid = false;
        }
        
        if(!validateUserName(user.getUserName())) {
        	System.out.println("Error:Product Category is invalid. Only letters and spaces are allowed.");
            isValid = false;
        }
        
        if(!validateMobileNo(user.getMobileNo())) {
        	System.out.println("Error");
            isValid = false;
        }
        
        if(!validateEmail(user.getEmail())) {
        	System.out.println("Error : Email format is incorrect");
            isValid = false;
        }

        return isValid;
    }
}
