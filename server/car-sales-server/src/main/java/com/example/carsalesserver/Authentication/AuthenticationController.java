package com.example.carsalesserver.Authentication;


import com.example.carsalesserver.AppUser.AppUser;
import com.example.carsalesserver.AppUser.utils.AppUserDAO;
import com.example.carsalesserver.security.config.JWTTokenHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final JWTTokenHelper jwtTokenHelper;
    private UserDetailsService userDetailsService;

//    @Autowired
//    public AuthenticationController(AuthenticationManager authenticationManager, JWTTokenHelper jwtTokenHelper) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenHelper = jwtTokenHelper;
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUserName(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("asd " + authentication.getAuthorities() + "asd");

            AppUser user = (AppUser) authentication.getPrincipal();
            String jwtToken = jwtTokenHelper.generateToken(user.getUsername());

            AuthenticationResponse response = new AuthenticationResponse();
            response.setToken(jwtToken);


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(Principal appUser){
        AppUser appUserObj = (AppUser) userDetailsService.loadUserByUsername(appUser.getName());

        AppUserDAO appUserDAO = new AppUserDAO();
        appUserDAO.setFirstName(appUserObj.getFirstName());
        appUserDAO.setLastName(appUserObj.getLastName());
        appUserDAO.setUserName(appUserObj.getUsername());
        appUserDAO.setWishlist(appUserObj.getWishlist());
        appUserDAO.setRole(appUserObj.getAppUserRole().name());
        appUserDAO.setAds(appUserObj.getAds());

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        AppUser user = (AppUser) authentication.getPrincipal();
//        return user.getUsername() + "\n" + user.getRoles();

        return ResponseEntity.ok(appUserDAO);

    }

}
