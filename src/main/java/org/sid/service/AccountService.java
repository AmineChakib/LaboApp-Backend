package org.sid.service;

import org.sid.entities.AppRole;
import org.sid.entities.User;

public interface AccountService {
	public User saveUser(User user);
	public AppRole saveRole(AppRole role);
	public void AddRoleToUser(String username, String roleName);
	public User findUserByUsername(String username);
}
