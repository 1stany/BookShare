package org.projectwork.bookshare.controller;

import org.projectwork.bookshare.dto.*;
import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.City;
import org.projectwork.bookshare.model.Item;
import org.projectwork.bookshare.model.UserPrincipal;
import org.projectwork.bookshare.model.services.abstractions.AppUserService;
import org.projectwork.bookshare.model.services.abstractions.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppUserController {
    private AppUserService appUserService;
    private JwtService jwtService;
    AuthenticationManager authenticationManager;

    @Autowired
    public AppUserController(AppUserService appUserService, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.appUserService = appUserService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public RegisterDto register(@RequestBody RegisterDto registerUser) {
        City city = appUserService.getCityByName(registerUser.getUser().getCityName());
        AppUser user = registerUser.toAppUser(city);
        AppUser savedUser = appUserService.saveUser(user);
        AppUserDto userDto = new AppUserDto(savedUser);
        return new RegisterDto(userDto, null);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginInfoDto login) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        if (authentication.isAuthenticated())
            return ResponseEntity.ok(new TokenDto(jwtService.generateToken(login.getEmail()), null));
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenDto(null, "Login Failed"));

    }

    @GetMapping("/user")
    public ResponseEntity<?> getLoggedUser(@AuthenticationPrincipal UserPrincipal principal) {
        try {
            AppUser result = appUserService.getUserById(principal.getUserId());
            AppUserDto userDto = new AppUserDto(result);
            return ResponseEntity.ok().body(userDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/user/city")
    public ResponseEntity<?> updateUserCity(@AuthenticationPrincipal UserPrincipal principal,
            @RequestParam long cityId) {
        try {
            AppUser updatedUser = appUserService.updateUserCity(principal.getUserId(), cityId);
            return ResponseEntity.ok(new AppUserDto(updatedUser));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
