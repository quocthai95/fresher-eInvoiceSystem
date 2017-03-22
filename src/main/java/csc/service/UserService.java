package csc.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import csc.models.Users;

public interface UserService {
	
	Users findById(long id);
	
	/*@Cacheable("users")*/
	Users findByName(String name);
	
	void saveUser(Users user);
	
	@CacheEvict(value = "users", allEntries = true)
	void updateUser(Users user);
	
	void deleteUserById(long id);

	Page<Users> findAllUsers(Pageable pageable); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(Users user);
	
	Page<Users> findByActiveAndUsernameContaining(String active, String username, Pageable pageable);
	
	Page<Users> findByUsernameContaining(String username, Pageable pageable);

			
}
