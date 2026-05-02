package org.projectwork.bookshare.controller;

import org.projectwork.bookshare.dto.ItemDto;
import org.projectwork.bookshare.dto.ItemTypeDto;
import org.projectwork.bookshare.exceptions.EntityNotFoundException;
import org.projectwork.bookshare.model.AppUser;
import org.projectwork.bookshare.model.Category;
import org.projectwork.bookshare.model.ItemType;
import org.projectwork.bookshare.model.UserPrincipal;
import org.projectwork.bookshare.model.services.abstractions.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {


    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }



    @GetMapping("/user/{userId}/wishlist")
    public ResponseEntity<?> getWishlistByUser(@PathVariable long userId){
        try {
            List<ItemType> wishlist = wishlistService.getWishlistByUserId(userId);
            return ResponseEntity.ok(wishlist.stream().map(ItemTypeDto::new).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/my-wishlist")
    public ResponseEntity<?> getMyWishlist(@AuthenticationPrincipal UserPrincipal principal){
        try {
            List<ItemType> wishlist = wishlistService.getWishlistByUserId(principal.getUserId());
            return ResponseEntity.ok(wishlist.stream().map(ItemTypeDto::new).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/user/itemType")
    public ResponseEntity<Boolean> addItemTypeForLoggedUser(@AuthenticationPrincipal UserPrincipal principal, @RequestBody ItemTypeDto itemTypeDto){
        try{
            Category category = wishlistService.findCategoryByName(itemTypeDto.getCategoryName());
            ItemType itemType = itemTypeDto.toItemType(category);
            boolean added = wishlistService.addItemType(principal.getUserId(), itemType);
            return ResponseEntity.ok(added);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/user/itemType/{itemTypeId}")
    public ResponseEntity<Boolean> removeItemTypeById(@AuthenticationPrincipal UserPrincipal principal, @PathVariable int itemTypeId){
        try{
            boolean removed = wishlistService.removeItemTypeById(principal.getUserId(), itemTypeId);
            return ResponseEntity.ok(removed);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/user/wishlist/clear")
    public ResponseEntity<Boolean> clearWishlist(@AuthenticationPrincipal UserPrincipal principal){
        boolean cleared = wishlistService.clearWishlist(principal.getUserId());
        return ResponseEntity.ok(cleared);
    }






}
