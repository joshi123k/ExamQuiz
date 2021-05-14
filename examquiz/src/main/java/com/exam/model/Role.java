package com.exam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Role {

    @Id
    private Long rolId;

    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    @JsonIgnore
    private Set<UserRole> userRoles=new HashSet<>();

    public Role() {
    }

    public Role(Long rolId, String roleName, Set<UserRole> userRoles) {
        this.rolId = rolId;
        this.roleName = roleName;
        this.userRoles = userRoles;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rolId=" + rolId +
                ", roleName='" + roleName + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
