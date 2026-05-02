package org.projectwork.bookshare.model.services.implementations;

import org.projectwork.bookshare.model.ItemTrade;
import org.projectwork.bookshare.model.repositories.abstractions.*;
import org.projectwork.bookshare.model.services.abstractions.TradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeServiceImp implements TradeService {
    private ItemTradeRepository tradeRepo;

    public TradeServiceImp(ItemTradeRepository tradeRepo){
        this.tradeRepo = tradeRepo;
    }

    @Override
    public List<ItemTrade> getAll() {
        return tradeRepo.findAll();
    }

    @Override
    public List<ItemTrade> getAllById(long id) {
        return tradeRepo.findAllById(id);
    }

    @Override
    public List<ItemTrade> getAllByRequestDate(LocalDate requestDate) {
        return tradeRepo.findByRequestDate(requestDate);
    }

    @Override
    public List<ItemTrade> getAllByExchangeDate(LocalDate exchangeDate) {
        return tradeRepo.findByExchangeDate(exchangeDate);
    }

    @Override
    public List<ItemTrade> getAllByItem(String itemName) {
        return null;
    }

    @Override
    public ItemTrade saveItemTrade(ItemTrade it) {
        return tradeRepo.save(it);
    }

    @Override
    public List<ItemTrade> getByHomeUserIdAndAccepted(long homeUserId, Boolean accepted) {
        if(accepted==null){
            return tradeRepo.findAllByHomeUserId(homeUserId);
        } else if(accepted == true){
            return tradeRepo.findByHomeUserIdAndAcceptedTrue(homeUserId);
        }else {
            return tradeRepo.findByHomeUserIdAndAcceptedFalse(homeUserId);
        }
    }


}
