package com.apisae.api.services.user;

import com.apisae.api.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceUser implements IServiceUser {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmailIs(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Utilisateur avec le mail %s n'a pas été trouvé",email)));
    }

}
