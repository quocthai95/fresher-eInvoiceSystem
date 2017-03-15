package csc.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import csc.models.Users;

public interface UserService {
	
	Users findById(long id);
	
	@Cacheable("users")
	Users findByName(String name);
	
	void saveUser(Users user);
	
	void updateUser(Users user);
	
	void deleteUserById(long id);

	Page<Users> findAllUsers(Pageable pageable); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(Users user);
	
	Page<Users> findByActive(String active, Pageable pageable);
			
}
