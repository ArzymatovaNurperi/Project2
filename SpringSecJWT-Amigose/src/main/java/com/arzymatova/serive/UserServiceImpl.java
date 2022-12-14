package com.arzymatova.serive;

import com.arzymatova.domain.Role;
import com.arzymatova.domain.User;
import com.arzymatova.repo.RoleRepository;
import com.arzymatova.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User save(User user) {
        log.info("saving new user-{} to database",user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role-{} to database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role-{} to user-{}",roleName,username);
        User user=userRepository.findByUsername(username);
      Role role=roleRepository.findByName(roleName);
      user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("fetching user-{}",username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all user");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if (user==null){
           log.error("User not found in database");
           throw new UsernameNotFoundException("User not found in database");
        }else {
          log.info("User found in database-{}",username);
        }
        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));} );
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
