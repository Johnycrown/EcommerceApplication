package com.pheonix.pheonix.security;

import com.pheonix.pheonix.data.model.AppUser;
import com.pheonix.pheonix.data.model.Authority;
import com.pheonix.pheonix.data.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RDBUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByEmail(username).orElse(null);
        if(appUser == null){
            throw new UsernameNotFoundException("this user with " + username + "does not exist");
        }


        return new User(appUser.getEmail(), appUser.getPassword(), getAuthorities(appUser.getAuthorities()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Authority> authorities) {

        return authorities.stream().map(authority -> {
            return new SimpleGrantedAuthority(authority.name());
        }).collect(Collectors.toList());
    }


}
