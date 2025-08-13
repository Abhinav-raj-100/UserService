package com.rajdevhub.userservice.service;


import com.rajdevhub.userservice.entities.UserInfo;
import com.rajdevhub.userservice.entities.UserInfoDto;
import com.rajdevhub.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto)
    {
        Function<UserInfo,UserInfo> updatingUser = user->{
            // todo implement user update logic
            return userRepository.save(userInfoDto.dtoToUserInfo());
        };

        Supplier<UserInfo> createUser = ()->
        {
            return userRepository.save(userInfoDto.dtoToUserInfo());
        };

        UserInfo userInfo = userRepository.findByUserId(userInfoDto.getUserId())
                .map(updatingUser)
                .orElseGet(createUser);

        return UserInfoDto.builder()
                .userId(userInfo.getUserId())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .email(userInfo.getEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .profilePic(userInfo.getProfilePic())
                .build();
    }


    public UserInfoDto getUser(UserInfoDto userInfoDto) throws Exception
    {
        Optional<UserInfo> user = this.userRepository.findByUserId(userInfoDto.getUserId());

        if(user.isEmpty())
        {
            throw new Exception("User Not Found");
        }

        UserInfo userInfo = user.get();
        return new UserInfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }
}
