package com.apisae.api.services.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IServiceUser extends UserDetailsService {

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
