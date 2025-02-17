package com.offnine.blogg.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be")
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    
    private String password;

    @NotNull
    private int age;

    @NotEmpty
    private String gender;

}
