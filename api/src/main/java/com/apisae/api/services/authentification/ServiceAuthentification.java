package com.apisae.api.services.authentification;

import com.apisae.api.exceptions.NotUniqueUserEx;
import com.apisae.api.models.authentification.ReponseAuth;
import com.apisae.api.models.authentification.RequeteAuth;
import com.apisae.api.models.authentification.RequeteCreationCompte;
import com.apisae.api.models.user.User;
import com.apisae.api.repositories.user.UserRepository;
import com.apisae.api.services.security.IServiceJWT;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAuthentification implements IServiceAuthentification {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final IServiceJWT serviceJWT;

    private final AuthenticationManager authenticationManager;

    @Override
    public ReponseAuth creerCompte(@NonNull RequeteCreationCompte requete) {
        User user = new User(
                requete.getEmail(),
                requete.getNom(),
                requete.getPrenom(),
                passwordEncoder.encode(requete.getPassword()),
                requete.getVille(),
                requete.getDateNaissance()
        );
        try {
            userRepository.save(user);
        }catch (DataAccessException e){
            throw new NotUniqueUserEx("Il existe déjà un utilisateur avec ce mail");
        }
        String jwtToken = serviceJWT.generateToken(user);
        return ReponseAuth.builder().token(jwtToken).build();
    }

    @Override
    public ReponseAuth authentifier(@NonNull RequeteAuth requete) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requete.getEmail(),
                            requete.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Email ou mot de passe incorrect");
        }
        User user = userRepository.findUserByEmailIs(requete.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Utilisateur avec le mail %s n'a pas été trouvé",requete.getEmail())));
        String jwtToken = serviceJWT.generateToken(user);
        return ReponseAuth.builder().token(jwtToken).build();
    }


}
