package com.rajdevhub.userservice.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserInfo {



    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @JsonProperty(namespace = "user_id")
    private String userId;

    @JsonProperty(namespace = "first_name")
    @NonNull
    private String firstName;

    @JsonProperty(namespace = "last_name")
    @NonNull
    private String lastName;

    @JsonProperty(namespace = "phone_number")
    @NonNull
    private Long phoneNumber;

    @JsonProperty(namespace = "email")
    @NonNull
    private String email;

    private String profilePic;


}
