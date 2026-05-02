package org.projectwork.bookshare.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "object_type")
public class ItemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @Column (name = "date_added")
    private LocalDate dateAdded;
    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn (name = "wishing_user_id")
    private AppUser wishingUser;


    public ItemType() {}

    public ItemType(long id, String name, String description, LocalDate dateAdded, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateAdded = dateAdded;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public AppUser getWishingUser() {
        return wishingUser;
    }

    public void setWishingUser(AppUser wishingUser) {
        this.wishingUser = wishingUser;
    }

    public Category getCategory() {
        return category;
    }
}
