package com.arzymatova.serive;

import com.arzymatova.domain.Role;
import com.arzymatova.domain.User;

import java.util.List;

public interface UserService {
    User save(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User>getUsers();

}
