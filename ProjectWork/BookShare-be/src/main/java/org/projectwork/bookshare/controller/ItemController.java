package org.projectwork.bookshare.controller;

import org.projectwork.bookshare.dto.ItemDto;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.Category;
import org.projectwork.bookshare.model.Item;
import org.projectwork.bookshare.model.services.abstractions.AppUserService;
import org.projectwork.bookshare.model.services.abstractions.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class ItemController {
    private ItemService itemService;
    private AppUserService userService;

    @Autowired
    public ItemController(ItemService itemService, AppUserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @PostMapping("/add-item")
    public ItemDto addNewItem(@RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("condition") String condition,
            @RequestParam("conditionComment") String conditionComment,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("ownerEmail") String ownerEmail,
            @RequestParam("creationDate") String creationDate,
            @RequestParam("activetrade") boolean activetrade,
            @RequestParam(value = "file", required = false) MultipartFile coverFile) throws IOException {

        // Salviamo il file della copertina se presente
        String coverPath = null;
        if (coverFile != null && !coverFile.isEmpty()) {
            coverPath = itemService.saveItemCover(coverFile);
        }

        // Recuperiamo categoria e proprietario
        Category category = itemService.findCategoryByName(categoryName);
        AppUser owner = userService.getUserByEmail(ownerEmail);

        // Convertiamo la data
        LocalDate parsedDate = LocalDate.parse(creationDate, DateTimeFormatter.ofPattern("uuuu-MM-dd"));

        // Creiamo l'item
        Item item = new Item(0, name, condition, description, conditionComment, false, category, parsedDate, owner,
                coverPath);
        Item savedItem = itemService.saveItem(item);

        return new ItemDto(savedItem);
    }
}
