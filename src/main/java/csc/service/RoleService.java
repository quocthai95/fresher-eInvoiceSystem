package csc.service;

import org.springframework.cache.annotation.Cacheable;

import csc.models.Role;

public interface RoleService {
	@Cacheable("invoice")
	Role findByName(String name);
}
