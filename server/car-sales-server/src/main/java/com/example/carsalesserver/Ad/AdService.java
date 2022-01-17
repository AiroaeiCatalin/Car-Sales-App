package com.example.carsalesserver.Ad;

import com.example.carsalesserver.AppUser.AppUser;
import com.example.carsalesserver.Car.Car;
import com.example.carsalesserver.Wishlist.Wishlist;
import com.example.carsalesserver.Wishlist.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdService {

    private AdRepository adRepository;
    private UserDetailsService userDetailsService;
    private WishlistRepository wishlistRepository;

    public List<Ad> getAds() {
        return adRepository.findAll();
    }


    public void addNewAd(Ad ad) {
        AppUser appUserObj = (AppUser) userDetailsService.loadUserByUsername(ad.getAppUser());
        ad.setAppUser(appUserObj);
        adRepository.save(ad);
    }
}
