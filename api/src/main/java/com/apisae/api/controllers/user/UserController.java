/**
 * @author Mauro Simoes
 */

package com.apisae.api.controllers.user;

import com.apisae.api.models.user.UserDTO;


import com.apisae.api.services.user.IServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:19006"})
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final IServiceUser serviceUser;


    /**
     * Recupere les information de l'utilisateur qui a fait la requete
     *
     * @return les informations de l'utilisateur
     */
    @GetMapping("/get-user")
    public ResponseEntity<UserDTO> getUser(){
        UserDTO userDTO;
        try{
            userDTO = serviceUser.getUserDTO();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userDTO);
    }
}
