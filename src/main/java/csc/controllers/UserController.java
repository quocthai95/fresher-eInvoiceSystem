package csc.controllers;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import csc.models.*;
import csc.service.CustomerService;
import csc.service.RoleService;
import csc.service.UserService;

/**
 * A class to test interactions with the SQLSERVER database using the UserDao
 * class.
 *
 */
@RestController
public class UserController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Resource
	@Qualifier("roleService")
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/user/getAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Users>> listAllUsers(Pageable pageable) {
    	Page<Users> users = userService.findAllUsers(pageable);
        if(users.getSize() == 0){
            return new ResponseEntity<Page<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Page<Users>>(users, HttpStatus.OK);
    }
  
  
     
    //-------------------Retrieve Single User--------------------------------------------------------
      
    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Users user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
    
  //-------------------Retrieve Single User--------------------------------------------------------
    
    @RequestMapping(value = "/user/getByActive/{active}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getUserByActive(@PathVariable("active") String active) {
        System.out.println("Fetching User with id " + active);
        List<Users> user = userService.findByActive(active);
        if (user == null) {
            System.out.println("User with id " + active + " not found");
            return new ResponseEntity<List<Users>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Users>>(user, HttpStatus.OK);
    }
  
      
      
    //-------------------Create a User--------------------------------------------------------
      
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Users user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());
  
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        userService.saveUser(user);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a User --------------------------------------------------------
      
    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
        System.out.println("Updating User " + id);
          
        Users currentUser = userService.findById(id);
          
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
  
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setActive(user.getActive());
          
        userService.updateUser(currentUser);
        return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a User --------------------------------------------------------
      
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Users> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
  
        Users user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
  
        userService.deleteUserById(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
    
  //------------------- Register  --------------------------------------------------------
    
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody Register res,    UriComponentsBuilder ucBuilder) {
        System.out.println("Register " + res.getUsername());
  
        Users user = new Users();
		user.setUsername(res.getUsername());
		user.setActive("0");
		user.setPassword(passwordEncoder.encode(res.getPassword()));
		HashSet<Role> roles = new HashSet<>();
		roles.add(roleService.findByName("ROLE_MEMBER"));
		user.setRoles(roles);		
		userService.saveUser(user);
		
		Customer cus = new Customer();
		cus.setUser(user);
		cus.setAddress("");
		cus.setEmail(res.getEmail());
		cus.setNameCustomer(res.getName());
		cus.setIdCustomer("" + user.getId());
		cus.setPhone(Integer.parseInt(res.getPhone()));
		cus.setTaxCode(Integer.parseInt("0"));
		cus.setLimitConsume(BigDecimal.valueOf(0));
		customerService.saveCustomer(cus);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(res.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
        

} // class UserController
