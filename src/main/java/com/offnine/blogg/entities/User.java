package com.offnine.blogg.entities;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  int id;
    @Column
    @Size(min=4,message = "Name must of 4 character")
    private  String name;

    
    @Column
    @Email
    @Email(message = "Email address is not Valid")
    private  String email;

    @Column
    @Size(min=3,max=10,message = "Password must be minimum 3 character")
    private  String password;

    @Column
    private  int age;

    @Column
    private  String gender;
@OneToMany(mappedBy ="user",cascade =CascadeType.ALL ,fetch = FetchType.LAZY )
    private List<Post> posts = new ArrayList<>();


}
