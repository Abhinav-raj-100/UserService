package com.rajdevhub.userservice.consumer;

import com.rajdevhub.userservice.entities.UserInfo;
import com.rajdevhub.userservice.entities.UserInfoDto;
import com.rajdevhub.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(UserInfoDto eventData)
    {
        try
        {
            UserInfo userInfo = eventData.dtoToUserInfo();
            userService.createOrUpdateUser(eventData);
            System.out.println("hello");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }
    }

//    UserInfoDto----> UserInfo need to be implemented to save in db by userservice kafka consumer

}
