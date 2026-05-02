package org.projectwork.bookshare.dto;

import org.projectwork.bookshare.model.Category;
import org.projectwork.bookshare.model.ItemType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ItemTypeDto {

    private long id;
    private String name;
    private String description;
    private String dateAdded;
    private String categoryName;

    public ItemTypeDto(ItemType it) {
        this.id = it.getId();
        this.name = it.getName();
        this.description = it.getDescription();
        this.dateAdded = it.getDateAdded().format(DateTimeFormatter.ofPattern("d-MMM-uuuu"));
        this.categoryName = it.getCategory().getName();
    }

    public ItemTypeDto() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemType toItemType(Category category) {
        return new ItemType(this.id, this.name, this.description, LocalDate.parse(dateAdded, DateTimeFormatter.ofPattern("uuuu-MM-dd")), category);
    }
}
