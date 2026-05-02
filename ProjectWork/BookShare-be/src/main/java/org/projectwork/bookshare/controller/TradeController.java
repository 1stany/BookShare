package org.projectwork.bookshare.controller;

import org.projectwork.bookshare.dto.ItemTradeDto;
import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.Item;
import org.projectwork.bookshare.model.ItemTrade;
import org.projectwork.bookshare.model.UserPrincipal;
import org.projectwork.bookshare.model.services.abstractions.AppUserService;
import org.projectwork.bookshare.model.services.abstractions.ItemService;
import org.projectwork.bookshare.model.services.abstractions.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {
    private TradeService tradeService;
    private AppUserService appUserService;
    private ItemService itemService;

    @Autowired
    public TradeController(TradeService tradeService, AppUserService appUserService, ItemService itemService) {
        this.tradeService = tradeService;
        this.appUserService = appUserService;
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItemTrade(@RequestBody ItemTradeDto itemTradeDto) throws EntityNotFoundException {
        try {
            Item requestedItem = itemService.findItemById(itemTradeDto.getRequestedItemId());
            Item exchangedItem = itemService.findItemById(itemTradeDto.getExchangedItemId());
            AppUser requestingUser = appUserService.getUserByEmail(itemTradeDto.getRequestingUserEmail());
            AppUser homeUser = appUserService.getUserByEmail(itemTradeDto.getHomeUserEmail());
            System.out.println(homeUser);
            System.out.println(requestingUser);
            ItemTrade itemTrade = itemTradeDto.toItemTrade(requestedItem, requestingUser, exchangedItem, homeUser);
            ItemTrade savedItemTrade = tradeService.saveItemTrade(itemTrade);
            ItemTradeDto tradeDto = new ItemTradeDto(savedItemTrade);
            return ResponseEntity.ok().body(tradeDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/requesting")
    public ResponseEntity<?> getHomeUserTradeForLoggedUser(
                                                           @RequestParam(required = false) Boolean accepted,
                                                           @AuthenticationPrincipal UserPrincipal principal){
         try{
             List <ItemTrade> trades = tradeService.getByHomeUserIdAndAccepted(principal.getUserId(), accepted);
             return ResponseEntity.ok().body(trades.stream().map(ItemTradeDto::new).toList());
         }catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
         }
    }

}
