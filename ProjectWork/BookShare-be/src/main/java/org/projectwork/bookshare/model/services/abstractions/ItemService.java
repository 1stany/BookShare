package org.projectwork.bookshare.model.services.abstractions;

import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.Category;
import org.projectwork.bookshare.model.City;
import org.projectwork.bookshare.model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ItemService {
    Item saveItem(Item item);

    Category findCategoryByName(String name);

    City findCityByName(String name);

    Item findItemById(long id) throws EntityNotFoundException;

    String saveItemCover(MultipartFile file) throws IOException;
}
