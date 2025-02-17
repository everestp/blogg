package com.offnine.blogg.entities;
import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class User  implements UserDetails {

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


@ManyToMany(cascade=CascadeType.ALL,fetch =FetchType.EAGER)
@JoinTable(
    name = "user_role",
    joinColumns =@JoinColumn(name = "user",referencedColumnName ="id"),
    inverseJoinColumns = @JoinColumn(name="role",referencedColumnName ="id")
)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
