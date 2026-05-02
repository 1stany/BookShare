package org.projectwork.bookshare.model.repositories.abstractions;

import org.projectwork.bookshare.dto.AppUserDto;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
    List<ItemType> findAllById(long id);
    List<ItemType> findByWishingUser(AppUser user);
}
