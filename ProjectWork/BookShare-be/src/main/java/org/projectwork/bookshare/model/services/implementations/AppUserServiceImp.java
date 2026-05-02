package org.projectwork.bookshare.model.services.implementations;

import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.City;
import org.projectwork.bookshare.model.repositories.abstractions.AppUserRepository;
import org.projectwork.bookshare.model.repositories.abstractions.CityRepository;
import org.projectwork.bookshare.model.services.abstractions.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImp implements AppUserService {
    private AppUserRepository appUserRepo;
    private CityRepository cityRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public AppUserServiceImp(AppUserRepository appUserRepo, CityRepository cityRepo) {
        this.appUserRepo = appUserRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public AppUser saveUser(AppUser u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return appUserRepo.save(u);
    }

    @Override
    public AppUser getUserById(long id) throws EntityNotFoundException {
        Optional<AppUser> u = appUserRepo.findById(id);
        if (u.isEmpty()) {
            throw new EntityNotFoundException(u.getClass(), id);
        }
        return u.get();
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return appUserRepo.findByEmail(email);
    }

    @Override
    public City getCityByName(String name) {
        return cityRepo.findByName(name);
    }

    @Override
    public AppUser updateUserCity(long userId, long cityId) throws EntityNotFoundException {
        AppUser user = getUserById(userId);
        City city = cityRepo.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(City.class, cityId));
        user.setCity(city);
        return appUserRepo.save(user);
    }
}
