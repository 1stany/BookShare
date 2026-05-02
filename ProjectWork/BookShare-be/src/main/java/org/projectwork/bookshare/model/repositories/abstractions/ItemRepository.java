package org.projectwork.bookshare.model.repositories.abstractions;

import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByActivetrade(boolean activetrade);
    List<Item> findByCondition(String condition);
    List<Item> findByConditionAndActivetrade(String condition, boolean activetrade);
    List<Item> findByOwnerId(long userId);
    List<Item> findByCategoryId(long categoryId);
    Page<Item> findAllByOrderByCreationDateDesc(Pageable pageable);
    Item findByName(String name);
}
