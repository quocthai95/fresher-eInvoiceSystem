package csc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import csc.models.Users;

public interface UserService {
	
	Users findById(long id);
	
	Users findByName(String name);
	
	void saveUser(Users user);
	
	void updateUser(Users user);
	
	void deleteUserById(long id);

	Page<Users> findAllUsers(Pageable pageable); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(Users user);
	
	List<Users> findByActive(String active);
			
}
