package org.projectwork.bookshare.controller;

import org.projectwork.bookshare.dto.ItemDto;
import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.Item;
import org.projectwork.bookshare.model.UserPrincipal;
import org.projectwork.bookshare.model.services.abstractions.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> searchItems (@RequestParam(required = false) String condition,
                                                      @RequestParam(required = false) Boolean activetrade,
                                                      @RequestParam(required = false) Integer lastN){
        List<Item> result = marketService.searchItems(condition, activetrade, lastN);
        return ResponseEntity.ok().body(result.stream().map(ItemDto::new).toList());
    }
    @GetMapping ("/item/{id}")
    public ResponseEntity<?> getItemById(@PathVariable int id) {
        try {
            Item result = marketService.findItemById(id);
            return ResponseEntity.ok().body(new ItemDto(result));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping ("/user/{id}/item")
    public ResponseEntity<?> getItemsByUser (@PathVariable int id) {
        try {
            List<Item> result = marketService.searchItemsByUser(id);
            return ResponseEntity.ok().body(result.stream().map(ItemDto::new).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping ("/user/item")
    public ResponseEntity<?> getItemsForLoggedUser (@AuthenticationPrincipal UserPrincipal principal) {
        try {
            System.out.println(principal.getUserId());
            List<Item> result = marketService.searchItemsByUser(principal.getUserId());
            return ResponseEntity.ok().body(result.stream().map(ItemDto::new).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @GetMapping ("/category/{id}/item")
    public ResponseEntity<?> getItemByCategory (@PathVariable int id){
        try{
            List<Item> result = marketService.searchItemsByCategory(id);
            return ResponseEntity.ok().body(result.stream().map(ItemDto::new).toList());
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
