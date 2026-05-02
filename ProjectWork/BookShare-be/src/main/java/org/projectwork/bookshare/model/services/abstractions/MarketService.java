package org.projectwork.bookshare.model.services.abstractions;

import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.*;

import java.util.List;
import java.util.Optional;

public interface MarketService {
List<Item> searchItemsByCategory(long categoryId) throws EntityNotFoundException;
//List<Item> searchItemsByCity(int cityId);
List<Item> searchItemsByUser(long userId) throws EntityNotFoundException;
//List<Item> searchItemsByName(String itemTypeName);
//List<Item> searchOfferedItems();
//Optional<AppUser> findUserById (int id);
List<Item> searchItems(String condition, Boolean activetrade, Integer lastN);

Item findItemById(long id) throws EntityNotFoundException;
}
