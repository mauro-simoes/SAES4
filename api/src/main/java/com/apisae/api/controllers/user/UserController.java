package com.apisae.api.controllers.user;

import com.apisae.api.models.user.UserDTO;


import com.apisae.api.services.user.IServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final IServiceUser serviceUser;


    @GetMapping("/get-user")
    public ResponseEntity<UserDTO> getUser(){
        UserDTO userDTO;
        try{
            userDTO = serviceUser.getUser();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userDTO);
    }
}
