package com.example.carsalesserver.Ad;


import com.example.carsalesserver.AWS.filestore.FileStore;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @ResponseBody
    public Long registerNewAd(@RequestBody Ad ad) {
        return adService.addNewAd(ad);

//        fileStore.save2();
    }

    @PostMapping(
            path = "/{adId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadAdImage(@PathVariable("adId") Long adId, @RequestParam("file") MultipartFile file) {
        System.out.println(adId);
        adService.uploadAdImage(adId, file);
    }

    @GetMapping("/{adId}/image/download")
    public byte[] downloadAdImage(@PathVariable("adId") Long adId){
        return adService.downloadAdImage(adId);
    }


}

