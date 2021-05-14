package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    // creating user

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    // getting username
    public User getUser(String username);

    // delete by id
    public void deleteUser(Long userId);

    // update by Id
    public User updateUser(User user,Long userId) ;
}
