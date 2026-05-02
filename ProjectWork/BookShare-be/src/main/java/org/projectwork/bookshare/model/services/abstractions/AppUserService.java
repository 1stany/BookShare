package org.projectwork.bookshare.model.services.abstractions;

import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.City;

public interface AppUserService {
    AppUser saveUser(AppUser u);

    AppUser getUserById(long id) throws EntityNotFoundException;

    AppUser getUserByEmail(String email);

    City getCityByName(String name);

    AppUser updateUserCity(long userId, long cityId) throws EntityNotFoundException;
}
