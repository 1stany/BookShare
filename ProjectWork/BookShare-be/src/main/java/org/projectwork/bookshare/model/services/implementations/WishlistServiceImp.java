package org.projectwork.bookshare.model.services.implementations;

import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.Category;
import org.projectwork.bookshare.model.ItemType;
import org.projectwork.bookshare.model.repositories.abstractions.*;
import org.projectwork.bookshare.model.services.abstractions.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImp implements WishlistService {
    private AppUserRepository appUserRepo;
    private ItemTypeRepository itemTypeRepo;
    private CategoryRepository catRepo;


    public WishlistServiceImp(AppUserRepository appUserRepo, ItemTypeRepository itemTypeRepo, CategoryRepository catRepo){
        this.appUserRepo = appUserRepo;
        this.itemTypeRepo = itemTypeRepo;
        this.catRepo = catRepo;
    }

    @Override
    public AppUser getUserById(long id) throws EntityNotFoundException
    {
        Optional<AppUser> u = appUserRepo.findById(id);
        if(u.isEmpty()){
            throw new EntityNotFoundException(u.getClass(), id);
            //throw new IllegalArgumentException("Utente non trovato");
        }
        return u.get();
    }

    @Override
    public Optional<ItemType> getItemTypeById(long id) {

        return itemTypeRepo.findById(id);
    }

    @Override
    public boolean addItemTypeById(long userId, long itemTypeId) throws EntityNotFoundException {
        List<ItemType> wishlist = getUserById(userId).getWishlist();
        Optional<ItemType> otpItem = getItemTypeById(itemTypeId);
        if (otpItem.isPresent() && wishlist.stream().filter(i->i.getId()==itemTypeId).findFirst().isEmpty()){
            wishlist.add(otpItem.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItemTypeById(long userId, long itemTypeId) throws EntityNotFoundException {
        Optional<ItemType> optItem = getItemTypeById(itemTypeId);
        if(optItem.isPresent()) {
            ItemType itemType = optItem.get();
            if(itemType.getWishingUser().getId() == userId) {
                itemTypeRepo.deleteById(itemTypeId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean clearWishlist(long userId) {
        Optional<AppUser> optUser = appUserRepo.findById(userId);
        if(optUser.isPresent()){
            optUser.get().getWishlist().clear();
            return true;
        } return false;
    }

    @Override
    public List<ItemType> getAllItemType() {
        return itemTypeRepo.findAll();
    }

    @Override
    public List<ItemType> getAllItemTypeByCategory(long categoryId) {
        return itemTypeRepo.findAllById(categoryId);
    }

    @Override
    public List<ItemType> getAllItemTypeByCity(long cityId) {
        return itemTypeRepo.findAllById(cityId);
    }

    @Override
    public boolean addItemType(long userId, ItemType itemType) throws EntityNotFoundException {
        AppUser user = getUserById(userId);
        if (user != null){
            itemType.setWishingUser(user);
            itemTypeRepo.save(itemType);
            return true;
        }
        return false;
    }

    @Override
    public List<ItemType> getWishlistByUserId(long id) throws EntityNotFoundException {
        Optional<AppUser> optU = appUserRepo.findById(id);
        if (optU.isPresent()){
            AppUser user = optU.get();
            return user.getWishlist();
        }
        throw new EntityNotFoundException(AppUser.class, id);

    }

    @Override
    public Category findCategoryByName(String name) {
        return catRepo.findByName(name);
    }
}
