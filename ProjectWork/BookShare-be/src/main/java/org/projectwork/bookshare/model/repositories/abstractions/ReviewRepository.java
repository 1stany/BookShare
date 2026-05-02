package org.projectwork.bookshare.model.repositories.abstractions;

import org.projectwork.bookshare.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
