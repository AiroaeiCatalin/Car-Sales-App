package com.example.carsalesserver.Ad;

import com.example.carsalesserver.AWS.bucket.BucketName;
import com.example.carsalesserver.AWS.filestore.FileStore;
import com.example.carsalesserver.AppUser.AppUser;
import com.example.carsalesserver.Car.Car;
import com.example.carsalesserver.Wishlist.Wishlist;
import com.example.carsalesserver.Wishlist.WishlistRepository;
import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class AdService {

    private AdRepository adRepository;
    private UserDetailsService userDetailsService;
    private FileStore fileStore;

    public List<Ad> getAds() {
        return adRepository.findAll();
    }


    public Long addNewAd(Ad ad) {
        AppUser appUserObj = (AppUser) userDetailsService.loadUserByUsername(ad.getAppUser());
        ad.setAppUser(appUserObj);
        return adRepository.save(ad).getId();
    }


    @Transactional
    public void uploadAdImage(Long adId, MultipartFile file) {
        //1. Check if image is not empty(de ex asta e un checked sau uncheked exception? ca pe net scrie ca e unchecked, adica deci puteam sa nu ii fac handle, puteam sa nu scriu nimic, sa nu ma refer deloc la ea?
        isFileEmpty(file);
        //2. If file is an image
        isImage(file);
        //3. The ad exists in our DB
        Ad ad = getAdOrThrow(adId);
        //4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);
        //5. Store the img in s3 and update DB (carImageLink) with s3 image link

        String path = String.format("%s/%s-%s-%s", BucketName.PROFILE_IMAGE.getBucketName(), ad.getId(), ad.getCar().getManufacturer(), ad.getCar().getModel());
        String filename = String.format("%s-%s", UUID.randomUUID(), file.getOriginalFilename());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            ad.setCarImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadAdImage(Long adId) {
        Ad ad = getAdOrThrow(adId);
        String path = String.format("%s/%s-%s-%s", BucketName.PROFILE_IMAGE.getBucketName(),
                ad.getId(), ad.getCar().getManufacturer(), ad.getCar().getModel());
       return ad.getCarImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }


    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        System.out.println(file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        System.out.println((file.getSize()));
        return metadata;
    }

    private Ad getAdOrThrow(Long adId) {
        return adRepository.findById(adId).orElseThrow(() -> new IllegalStateException(String.format("Ad with id %d doesn't exist in the DB", adId)));
    }

    private void isImage(MultipartFile file) {
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_WEBP.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Can't upload an empty file [ " + file.getSize() + "]");
        }
    }

}
