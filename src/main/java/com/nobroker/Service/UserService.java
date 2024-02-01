package com.nobroker.Service;

import com.nobroker.Entity.User;
import com.nobroker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User registerUser(User user){
        return userRepo.save(user);
    }

    public User getUserByEmail(String email) {
        return  userRepo.findByEmail(email);
    }

    public void verifyEmail(User user){
        user.setEmailVerified(true);
        userRepo.save(user);
    }
    public boolean isEmailVerified(String email) {
        User user = userRepo.findByEmail(email);
        return user != null && user.isEmailVerified();
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
