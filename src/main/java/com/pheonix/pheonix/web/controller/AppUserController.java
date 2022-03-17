package com.pheonix.pheonix.web.controller;

import com.pheonix.pheonix.data.dto.AppUserRequestDto;
import com.pheonix.pheonix.data.dto.AppUserResponseDto;
import com.pheonix.pheonix.data.model.AppUser;
import com.pheonix.pheonix.services.AppUser.AppUserService;
import com.pheonix.pheonix.web.exceptions.UserAlreadyEXist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/appuser")
public class AppUserController {
    @Autowired
    AppUserService appUserService;

@PostMapping
    public ResponseEntity<?>  createUser(@RequestBody AppUserRequestDto appUserRequestDto) throws UserAlreadyEXist {
    try{
        AppUserResponseDto createdUser = appUserService.createUser(appUserRequestDto);
        return  ResponseEntity.status(HttpStatus.OK).body(createdUser);
    } catch (UserAlreadyEXist userAlreadyEXist) {
        return ResponseEntity.badRequest().body(userAlreadyEXist.getMessage());
    }
}
}
