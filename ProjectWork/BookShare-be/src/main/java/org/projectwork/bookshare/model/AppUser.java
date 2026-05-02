package org.projectwork.bookshare.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String gender;
    private LocalDate birthdate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private String password;
    @OneToMany(mappedBy = "wishingUser")
    private List<ItemType> wishlist = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String firstname, String lastname, String email, String phone, String gender, LocalDate birthdate,
            String description, City city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.description = description;
        this.city = city;
    }

    public AppUser(String firstname, String lastname, String email, String phone, String gender, LocalDate birthdate,
            String description, City city, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.description = description;
        this.city = city;
        this.password = password;
    }

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getDescription() {
        return description;
    }

    public List<ItemType> getWishlist() {
        return wishlist;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encode) {
        this.password = encode;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

}
