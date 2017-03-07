package csc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import csc.models.Role;
import java.lang.String;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}
