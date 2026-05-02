package org.projectwork.bookshare.model;

import jakarta.persistence.*;
import org.projectwork.bookshare.dto.ItemDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "object")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private boolean activetrade;
    private String condition;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "condition_comment")
    private String conditionComment;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;
    private String cover;
    public static final List<String> CONDITIONS = Arrays.asList("comenuovo", "ottimo", "buono", "accettabile");

    public Item() {
    }

    public Item(long id, String name, String condition, String description, String conditionComment,
            boolean activetrade, Category category, LocalDate creationDate, AppUser owner) {
        this.id = id;
        this.name = name;
        this.condition = condition;
        this.description = description;
        this.conditionComment = conditionComment;
        this.activetrade = activetrade;
        this.category = category;
        this.creationDate = creationDate;
        this.owner = owner;
    }

    public Item(long id, String name, String condition, String description, String conditionComment,
            boolean activetrade, Category category, LocalDate creationDate, AppUser owner, String cover) {
        this(id, name, condition, description, conditionComment, activetrade, category, creationDate, owner);
        this.cover = cover;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getConditionComment() {
        return conditionComment;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getCondition() {
        return condition;
    }

    public AppUser getOwner() {
        return owner;
    }

    public boolean isActivetrade() {
        return activetrade;
    }

    public void setActivetrade(boolean activetrade) {
        this.activetrade = activetrade;
    }

    public Category getCategory() {
        return category;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
