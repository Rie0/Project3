package org.twspring.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

//VARIABLES
    //AUTHENTICATION VARS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min=4,max = 10,
            message = "Username must have between 4 to 10 characters")
    private String username;

    @Column(columnDefinition = "VARCHAR(300) NOT NULL")
    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{6,}$",
            message = "Password must be strong (at least: at least 6 characters, one uppercase letter, one lowercase letter, one number, and one special character)")
    private String password;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
    private String role;
    //USER INFO VARS
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    @Size(min=4,max = 10,
            message = "Name must have between 2 to 20 letters")
    private String name;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL UNIQUE")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must have a valid format")
    @Size(min=7,max = 50,
            message = "Email must have between 7 to 50 letters")
    private String email;

//RELATIONSHIPS
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Employee employee;

//USER DETAILS METHODS
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
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
