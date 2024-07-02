package com.chainsys.medik.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	@Autowired
	MedikDAO medikDAO;
	
	 @RequestMapping("/home")
    public String home() {
        return "pharmacyHome.jsp";
    }
	
	 
	
	 @GetMapping("/click")
	    public String saveStudent(@RequestParam("name") String userName, 
	            @RequestParam("mobileNumber") String mobileNo, @RequestParam("email") String email,
	            @RequestParam("password") String password, Model model)  {
		 	System.out.println("click");
		 	
		 	  if (medikDAO.isUserExists(email)) {
		            model.addAttribute("message", "User already exists");
		            return "pharmacyReg.jsp"; 
		        }
	        User user = new User();
	        user.setUserName(userName);
	        user.setMobileNo(mobileNo);
	        user.setEmail(email);
	        user.setPassword(password);
	        medikDAO.insertUser(user);



	        return "pharmacyLogin.jsp";
	    }
	 
	 @PostMapping("/login")
	 public String loginUser(@RequestParam("email") String email, 
	                         @RequestParam("password") String password, 
	                         HttpSession session,Model model) {

	     User user = medikDAO.findUserByEmailAndPassword(email, password);
	     if (user != null) {
	         session.setAttribute("email", user.getEmail());
	         session.setAttribute("name", user.getUserName());

	         if (email.endsWith("@medik.com")) {
	        	 System.out.println("admin");
	             return "redirect:/adminPage.jsp";
	         } else {
	             return "redirect:/pharmacyHome.jsp";
	         }
	     } else {
	    	 model.addAttribute("errorMessage", "Incorrect email or password");
	         return "pharmacyLogin.jsp";
	     }
	 }
	 @RequestMapping("/logout")
	 public String logoutUser(HttpSession session,HttpServletRequest request) {
		 session = request.getSession(false);
		 if (session != null) {
				session.invalidate(); 
			}

		 
		return "redirect:/pharmacyHome.jsp";
		 
	 }

}
