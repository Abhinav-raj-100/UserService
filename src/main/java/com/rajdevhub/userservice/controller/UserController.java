package com.rajdevhub.userservice.controller;

import com.rajdevhub.userservice.entities.UserInfoDto;
import com.rajdevhub.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/v1/createUpdate")
    public ResponseEntity<UserInfoDto> createUpdateMethod(@RequestBody UserInfoDto userInfoDto)
    {
        try
        {
            UserInfoDto userInfoDto1 = userService.createOrUpdateUser(userInfoDto);
            return  new ResponseEntity<>(userInfoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/v1/getUser")
    public ResponseEntity<UserInfoDto> getUser(UserInfoDto userInfoDto)
    {
        try {
            UserInfoDto user = userService.getUser(userInfoDto);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
