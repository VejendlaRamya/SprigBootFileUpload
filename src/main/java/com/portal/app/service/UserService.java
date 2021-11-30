package com.portal.app.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.portal.app.dto.RegisterUserDto;
import com.portal.app.model.Role;
import com.portal.app.model.User;
import com.portal.app.repository.RoleRepository;
import com.portal.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));

	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	public Map<String, String> getUserDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getPrincipal();
		auth.getAuthorities();
		Map<String, String> map = new HashMap<>();

		Object principal = auth.getPrincipal();

		String Username = ((UserDetails) principal).getUsername();

		if (auth.getAuthorities().toString().contains("USER")) {
			map.put("role", "USER");
		} else if (auth.getAuthorities().toString().contains("ADMIN")) {
			map.put("role", "ADMIN");
		}
		map.put("username", Username);

		// TODO Auto-generated method stub
		return map;
	}

	public String getUserRole() {
		String role = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth.getAuthorities().toString().contains("USER")) {
			role = "USER";
		} else if (auth.getAuthorities().toString().contains("ADMIN")) {
			role = "ADMIN";
		}
		return role;

	}

	public String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userName = ((UserDetails) principal).getUsername();
		return userName;
	}

	public User saveUser(RegisterUserDto registerUser) {
		User user = new User();
		Set<Role> roles = new HashSet<>();

		Role findByName = roleRepository.findByName(registerUser.getRole());
		roles.add(findByName);

		user.setPassword(bcryptEncoder.encode(registerUser.getPassword()));

		user.setEmail(registerUser.getEmail());
		user.setRoles(roles);
		user.setUsername(registerUser.getUsername());
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public List<User> getAllUserDetails() {
		return userRepository.getAllUserByUserRole("USER");
		// TODO Auto-generated method stub

	}

	public User getUserByName(String userName) {
		return userRepository.findByUsername(userName);
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

}
