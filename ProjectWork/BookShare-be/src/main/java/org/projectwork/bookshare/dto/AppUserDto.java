package org.projectwork.bookshare.dto;

import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.City;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AppUserDto {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String gender;
    private String birthdate;
    private String description;
    private String cityName;

    public AppUserDto() {}

    public AppUserDto(AppUser appUser) {
        this.id = appUser.getId();
        this.firstname = appUser.getFirstname();
        this.lastname = appUser.getLastname();
        this.email = appUser.getEmail();
        this.phone = appUser.getPhone();
        this.gender = appUser.getGender();
        this.birthdate = appUser.getBirthdate().format(DateTimeFormatter.ofPattern("d-MMM-uuuu"));
        this.description = appUser.getDescription();
        this.cityName = appUser.getCity().getName();
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getDescription() {
        return description;
    }

    public AppUser toAppUser(City city){
        return new AppUser(this.firstname, this.lastname, this.email, this.phone, this.gender, LocalDate.parse(this.birthdate, DateTimeFormatter.ofPattern("uuuu-MM-dd")), this.description, city);
    }
}
