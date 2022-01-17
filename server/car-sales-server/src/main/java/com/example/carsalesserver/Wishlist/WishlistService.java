package com.example.carsalesserver.Wishlist;

import com.example.carsalesserver.Ad.Ad;
import com.example.carsalesserver.Ad.AdRepository;
import com.example.carsalesserver.AppUser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WishlistService {

    private UserDetailsService userDetailsService;
    private WishlistRepository wishlistRepository;
    private AdRepository adRepository;

    @Transactional
    public void addToWishlist(WishlistAdDao wishlistAd) {
        AppUser appUserObj = (AppUser) userDetailsService.loadUserByUsername(wishlistAd.getUserName());
        Ad ad = adRepository.getById(wishlistAd.getAdId());
        Wishlist wishlist = wishlistRepository.getById(appUserObj.getId());
        System.out.println(ad.getId());
        System.out.println("Id wishlist: " + wishlist.getId());
        wishlist.addAd(ad);
    }
}
