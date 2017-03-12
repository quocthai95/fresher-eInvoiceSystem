package csc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface UserRepository extends CrudRepository<Users, Long> {

	Page<Users> findAll(Pageable pageable);

	Users findByUsername(String username);
	
	List<Users> findByActive(String active);
} // class UserDao
