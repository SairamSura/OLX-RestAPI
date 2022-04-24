package com.olx.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.entity.UserEntity;
import com.olx.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<UserEntity> userEntityList = userRepo.findByUserName(userName);
		if(userEntityList==null || userEntityList.size()==0) {
			throw new UsernameNotFoundException(userName);
		}
		UserEntity userEntity = userEntityList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		UserDetails userDetails = new User(userName, passwordEncoder.encode(userEntity.getPassword()),authorities);
		
		return userDetails;
	}
	
}
