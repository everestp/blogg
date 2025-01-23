package com.offnine.blogg.Payload;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {



    private  int id;

    private  String name;


    private  String email;

    private  String password;

    private  int age;

    private  String gender;




}
