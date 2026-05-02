package org.projectwork.bookshare.model.services.implementations;

import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.*;
import org.projectwork.bookshare.model.repositories.abstractions.*;
import org.projectwork.bookshare.model.services.abstractions.MarketService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImp implements MarketService {
    private AppUserRepository appUserRepo;
    private ItemRepository itemRepo;
    private CategoryRepository categoryRepo;

    public MarketServiceImp(AppUserRepository appUserRepo, ItemRepository itemRepo, CategoryRepository categoryRepo){
        this.appUserRepo = appUserRepo;
        this.itemRepo = itemRepo;
        this.categoryRepo = categoryRepo;
    }


    @Override
    public List<Item> searchItemsByCategory(long categoryId) throws EntityNotFoundException {
        Optional<Category> oC = categoryRepo.findById(categoryId);
        if(oC.isEmpty()){
            throw new EntityNotFoundException(oC.getClass(), categoryId);
        }
        return itemRepo.findByCategoryId(categoryId);
    }

    @Override
    public List<Item> searchItemsByUser(long userId) throws EntityNotFoundException{
        Optional<AppUser> oU = appUserRepo.findById(userId);
        if(oU.isEmpty()){
            throw new EntityNotFoundException(AppUser.class, userId);
        }
        return itemRepo.findByOwnerId(userId);
    }
//
//
//    @Override
//    public Optional<AppUser> findUserById(int id) {
//        return appUserRepo.findById(id);
//    }

    @Override
    public List<Item> searchItems(String condition, Boolean activetrade, Integer lastN) {
        if(lastN!=null){
            Pageable pageable = PageRequest.of(0,lastN);
            return itemRepo.findAllByOrderByCreationDateDesc(pageable).getContent();
        }
        if(condition!= null && !Item.CONDITIONS.contains(condition)) {
            throw new IllegalArgumentException("Condizione non valida");
        }
        if(condition==null && activetrade == null){
            return itemRepo.findAll();
        }
        if(condition == null && activetrade != null) {
            return itemRepo.findByActivetrade(activetrade);
        }
        if(condition != null && activetrade == null) {
            return itemRepo.findByCondition(condition);
        }
        return itemRepo.findByConditionAndActivetrade(condition, activetrade);
    }

    @Override
    public Item findItemById(long id) throws EntityNotFoundException {
        Optional<Item> optItem = itemRepo.findById(id);
        if(optItem.isEmpty()){
            throw new EntityNotFoundException(Item.class, id);
        }
        return optItem.get();
    }
}
