package org.projectwork.bookshare.dto;

import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.City;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterDto {
    private AppUserDto user;
    private String password;

    public RegisterDto(AppUserDto user, String password) {
        this.user = user;
        this.password = password;
    }

    public AppUser toAppUser(City city){
        return new AppUser(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPhone(), user.getGender(), LocalDate.parse(user.getBirthdate(), DateTimeFormatter.ofPattern("uuuu-MM-dd")), user.getDescription(), city, password);
    }

    public AppUserDto getUser(){
        return this.user;
    }

    public void setUser(AppUserDto user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
