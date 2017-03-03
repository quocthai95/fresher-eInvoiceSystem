package com.csc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.csc.model.User;
//import com.csc.model.UserDao;

@Controller
public class UserController {
	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /create --> Create a new user and save it in the database.
	 * @param email  User's email
	 * @param name User's name
	 * @return A string describing if the user is successfully created or not.
	 */
//	@RequestMapping("/create")
//	@ResponseBody
//	public String create(String email, String name) {
//		User user = null;
//		try {
//			user = new User(email, name);
//			userDao.save(user);
//		} catch (Exception ex) {
//			return "Error creating the user: " + ex.toString();
//		}
//		return "User succesfully created! (id = " + user.getId() + ")";
//	}
//	
//	
//	  // ------------------------
//	  // PRIVATE FIELDS
//	  // ------------------------
//
//	  @Autowired
//	  private UserDao userDao;
}
