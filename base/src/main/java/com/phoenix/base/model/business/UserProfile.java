package com.phoenix.base.model.business;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfile{
    private Long id;
    private String username;
    private String status;

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private Date hiredDate;

    private String title;
    private String department;
}
