package com.abhijeet.jobsite.global.auth;

import com.abhijeet.jobsite.global.users.UserRepo;
import com.abhijeet.jobsite.global.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);

        if (user != null)
            return new UserPrincipal(user);
        else
            throw new UsernameNotFoundException("User Not Found!");
    }
}
