package com.abhijeet.jobsite.global.users;

import com.abhijeet.jobsite.configs.ApiResponse;
import com.abhijeet.jobsite.global.auth.Auth;
import com.abhijeet.jobsite.global.auth.UserDetailServiceImpl;
import com.abhijeet.jobsite.global.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserDetailServiceImpl userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(@RequestBody Users user) {
        try {
            Users usr = userService.createUser(user);
            return new ResponseEntity<>(ApiResponse.success(
                    usr, HttpStatus.CREATED.value()),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.failure(
                    HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Auth>> login(@RequestBody Users user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtils.generateToken(userDetails.getUsername());
            Auth auth = new Auth();
            auth.setAccessToken(jwt);
            return new ResponseEntity<>(ApiResponse.success(auth), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.failure(e.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }
}
