package org.projectwork.bookshare.model.repositories.abstractions;

import org.projectwork.bookshare.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}
