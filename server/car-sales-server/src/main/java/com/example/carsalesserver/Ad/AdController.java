package com.example.carsalesserver.Ad;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/ad")
@AllArgsConstructor
public class AdController {

    private AdService adService;
//    private FileStore fileStore;

    @GetMapping
    public List<Ad> getAds(){
        return adService.getAds();
    }

    @PostMapping
    public void registerNewAd(@RequestBody Ad ad) {
//        System.out.println(ad.getCar());
        adService.addNewAd(ad);
//        fileStore.save2();
    }



}

