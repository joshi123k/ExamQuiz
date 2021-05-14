package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

    User local=this.userRepository.findByUsername(user.getUsername());

    if (local!=null)
    {
        System.out.println("User already exist");
        throw new Exception("User already exist");
    }else{

        // creating user

        for (UserRole ur:userRoles) {

            roleRepository.save(ur.getRole());
        }

        user.getUserRoles().addAll(userRoles);
        local=this.userRepository.save(user);

    }

      return local;

    }

    // getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

  // delete by userid
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }



    // user updated by id
    @Override
    public User updateUser(User user,Long userId)  {

        User olduser = this.userRepository.findById(userId).get();

//        olduser.setEmail(user.getEmail());
//        olduser.setFirstName(user.getFirstName());
//        olduser.setPassword(user.getPassword());
//        olduser.setLastName(user.getLastName());
          olduser.setPhone(user.getPhone());

        User user1 = this.userRepository.save(olduser);

        return user1;
    }
}
