package com.example.carsalesserver.Wishlist;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/wishlist")
@AllArgsConstructor
public class WishlistController {

    private WishlistService wishlistService;

    @PostMapping
    public void addToWishlist(@RequestBody WishlistAdDao wishlistAd){
        System.out.println(wishlistAd);
        wishlistService.addToWishlist(wishlistAd);
    }

}
