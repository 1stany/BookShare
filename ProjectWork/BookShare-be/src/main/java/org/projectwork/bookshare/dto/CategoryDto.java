package org.projectwork.bookshare.dto;

import org.projectwork.bookshare.model.Category;

public class CategoryDto {
    private long id;
    private String name;
    private String description;

    public CategoryDto() {}

    public CategoryDto(Category cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.description = cat.getDescription();
    }

    public Category toCategory(){
        return new Category(this.id, this.name, this.description);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
