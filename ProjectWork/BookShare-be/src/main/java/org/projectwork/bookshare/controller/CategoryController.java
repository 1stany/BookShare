package org.projectwork.bookshare.controller;

import org.projectwork.bookshare.model.Category;
import org.projectwork.bookshare.model.repositories.abstractions.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/market/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }
}
