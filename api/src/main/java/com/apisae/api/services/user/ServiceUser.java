package com.apisae.api.services.user;

import com.apisae.api.models.user.UserDTO;
import com.apisae.api.repositories.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceUser implements IServiceUser {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;


    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmailIs(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Utilisateur avec le mail %s n'a pas été trouvé",email)));
    }

    public UserDTO getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        return userRepository.findUserByEmailIs(email)
                .map(userDTOMapper)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Utilisateur avec le mail %s n'a pas été trouvé",email)));
    }

}
