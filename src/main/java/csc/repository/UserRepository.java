package csc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Users;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 */
// @Transactional
@Repository
public interface UserRepository extends CrudRepository<Users, Long>,JpaRepository<Users, Long> {

	Page<Users> findAll(Pageable pageable);
	
	Page<Users> findByActiveAndUsernameContaining(String active, String username, Pageable pageable);
	
	Page<Users> findByUsernameContaining(String username, Pageable pageable);

	Users findByUsername(String username);
	
	//Page<Users> findByActive(String active, Pageable pageable);
		
} // class UserDao
