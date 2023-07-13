package com.Ecommerce.acme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ecommerce.acme.model.Role;
import com.Ecommerce.acme.model.User;
import com.Ecommerce.acme.repository.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository ur;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = ur.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getText()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    
    public Optional<User> getUser(final int id){
		return ur.findById(id);
	}

    public void insertUser(User User) {
    	ur.save(User);
	}

    public User findByUsername(String username) {
        return ur.findByUsername(username);
    }

    public List<User> getAllUsers() { return ur.findAllByRole(Role.CLIENT); }

    public void removeUser(int userId) {
        ur.deleteById(userId);
    }

}