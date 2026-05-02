package org.projectwork.bookshare.model.repositories.abstractions;

import org.projectwork.bookshare.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
