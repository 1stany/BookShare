package org.projectwork.bookshare.model.repositories.abstractions;

import org.projectwork.bookshare.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
