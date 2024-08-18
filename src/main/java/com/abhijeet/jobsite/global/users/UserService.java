package com.abhijeet.jobsite.global.users;

import com.abhijeet.jobsite.global.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private UserRepo userRepo;

    public Users createUser(Users user) throws CustomException {
        Users usr = userRepo.findByUsername(user.getUsername());
        if (usr == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepo.save(user);
        } else {
            throw new CustomException("This username is already taken, please use different username!");
        }
    }
}
