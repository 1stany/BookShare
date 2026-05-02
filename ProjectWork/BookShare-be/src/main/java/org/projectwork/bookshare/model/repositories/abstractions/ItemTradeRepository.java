package org.projectwork.bookshare.model.repositories.abstractions;

import org.projectwork.bookshare.model.ItemTrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ItemTradeRepository extends JpaRepository<ItemTrade, Long> {
    List<ItemTrade> findAllById(long id);
    List<ItemTrade> findByRequestDate(LocalDate requestDate);
    List<ItemTrade> findByExchangeDate(LocalDate exchangeDate);
    List<ItemTrade> findAllByHomeUserId(long homeUserId);
    List<ItemTrade> findByHomeUserIdAndAcceptedTrue(long homeUserId);
    List<ItemTrade> findByHomeUserIdAndAcceptedFalse(long homeUserId);
}
