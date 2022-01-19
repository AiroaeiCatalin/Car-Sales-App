package com.example.carsalesserver.Ad;

import com.example.carsalesserver.AppUser.AppUser;
import com.example.carsalesserver.Car.Car;
import com.example.carsalesserver.Wishlist.Wishlist;
import com.example.carsalesserver.Wishlist.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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


    public Long addNewAd(Ad ad) {
        AppUser appUserObj = (AppUser) userDetailsService.loadUserByUsername(ad.getAppUser());
        ad.setAppUser(appUserObj);
        return adRepository.save(ad).getId();
    }

    public void uploadAdImage(Long adId, MultipartFile file) {
        //1. Check if image is not empty
        //2. If file is an image
        //3. The user exists in our DB
        //4. Grab some metadata from file if any
        //5. Store the img in s3 and update DB (carImageLink) with s3 image link

    }
}
