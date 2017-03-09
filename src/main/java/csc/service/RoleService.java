package csc.service;

import csc.models.Role;

public interface RoleService {
	Role findByName(String name);
}
