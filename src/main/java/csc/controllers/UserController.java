package csc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import csc.models.*;
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
	 * 
	 * @param email
	 *            User's email
	 * @param name
	 *            User's name
	 * @return A string describing if the user is succesfully created or not.
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(String email, String name) {
		Users user = null;
		try {
			// for (int i =0; i < 100; i++) {
			// email = "test" + i;
			// name = "test" + i;
			// user = new User(email, name);
			// userDao.save(user);
			// }
//			user = new User(email, name);
			userDao.save(user);
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created! (id = " + user.getId() + ")";
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is succesfully deleted or not.
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			Users user = new Users(id);
			userDao.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user: " + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * /get-by-email --> Return the id for the user having the passed email.
	 * 
	 * @param email
	 *            The email to search in the database.
	 * @return The user id or a message error if the user is not found.
	 */
//	@RequestMapping("/get-by-email")
//	@ResponseBody
//	public String getByEmail(String email) {
//		String userId;
//		try {
//			User user = userDao.findByEmail(email);
//			userId = String.valueOf(user.getId());
//		} catch (Exception ex) {
//			return "User not found";
//		}
//		return "The user id is: " + userId;
//	}

	/**
	 * /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 *
	 * @param id
	 *            The id for the user to update.
	 * @param email
	 *            The new email.
	 * @param name
	 *            The new name.
	 * @return A string describing if the user is succesfully updated or not.
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(long id, String email, String name) {
		try {
			Users user = userDao.findOne(id);
//			user.setEmail(email);
//			user.setName(name);
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
