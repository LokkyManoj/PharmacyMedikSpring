package com.chainsys.medik.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.medik.dao.MedikDAO;
import com.chainsys.medik.model.User;

import ch.qos.logback.core.model.Model;
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
	        User user = new User();
	        user.setUserName(userName);
	        user.setMobileNo(mobileNo);
	        user.setEmail(email);
	        user.setPassword(password);
	        medikDAO.insertUser(user);

//	        List<User> getDetails = medikDAO.listUsers();
//	        model.addAttribute("users", getDetails);

	        return "pharmacyLogin.jsp";
	    }
	 
	 @PostMapping("/login")
	    public String loginUser(@RequestParam("email") String email, 
	                            @RequestParam("password") String password, 
	                            Model model,HttpSession session) {
	        User user = medikDAO.findUserByEmailAndPassword(email, password);
	        if (user != null) {
	        	session.setAttribute("email", user.getEmail());
	            session.setAttribute("name", user.getUserName());

	            return "pharmacyHome.jsp";
	        } else {
	            return "pharmacyLogin.jsp";
	        }
	    }

}
