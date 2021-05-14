package com.exam.model;




import javax.persistence.*;

@Entity

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userroleId;

    // user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private  Role role;

    public UserRole() {
    }

    public UserRole(Long userroleId, User user, Role role) {
        this.userroleId = userroleId;
        this.user = user;
        this.role = role;
    }

    public Long getUserroleId() {
        return userroleId;
    }

    public void setUserroleId(Long userroleId) {
        this.userroleId = userroleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
