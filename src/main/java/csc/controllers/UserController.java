package csc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import csc.models.Users;
import csc.repository.UserRepository;

/**
 * A class to test interactions with the SQLSERVER database using the UserDao
 * class.
 *
 */
@Controller
public class UserController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /create --> Create a new user and save it in the database.
	 */
	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody Users user) {
		try {
			userDao.save(user);
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created! (id = " + user.getId() + ")";
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 */
	@RequestMapping(value = "/users/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(@PathVariable long id) {
		try {
			Users user = new Users(id);
			userDao.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user: " + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 */
	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestBody Users user) {
		try {
			Users tmp = userDao.findOne(user.getId());
			tmp.setPassword(user.getPassword());
			userDao.save(user);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public Page<Users> findAll(Pageable pageable) {
		Page<Users> users = userDao.findAll(pageable);
		return users;
	}
	// // ------------------------
	// // PRIVATE FIELDS
	// // ------------------------

	@Autowired
	private UserRepository userDao;

} // class UserController
