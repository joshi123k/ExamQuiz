package com.exam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    // @NotBlank(message = "Should not be Blank")
    // @Pattern(regexp = "^[a-zA-z ]{3,30}$", message = "Must start with alphabet only. Min Size 3 and Max size 30")
    private String firstName;

    private String lastName;

    // @Column(unique = true)
    // @NotBlank(message = "Should not be Blank")
    // @Pattern(regexp = "^(.+)@(.+)$", message = "invalid email")
    private String email;

    // @NotBlank(message = "Should not be blank")
//	@Pattern(regexp = "^[A-Za-z@_]{7,14}$", message = "Allow only character.Size between 7-14")
    private String password;

    private String phone;

    private String profile;


    private boolean enabled = true;

    // user many roles

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRoles=new HashSet<>();

    public User() {
    }


    public User(Long id, String username, String firstName, String lastName, String email, String password, String phone, String profile, boolean enabled, Set<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.profile = profile;
        this.enabled = enabled;
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set =new HashSet<>();
        this.userRoles.forEach(
                userRole->{
                    set.add(new Authority(userRole.getRole().getRoleName()));
                }
        );
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", profile='" + profile + '\'' +
                ", enabled=" + enabled +
                ", userRoles=" + userRoles +
                '}';
    }
}


