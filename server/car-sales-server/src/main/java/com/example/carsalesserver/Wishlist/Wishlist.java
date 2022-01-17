package com.example.carsalesserver.Wishlist;


import com.example.carsalesserver.Ad.Ad;
import com.example.carsalesserver.AppUser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Getter
@Setter
public class Wishlist {
    @Id
    @Column(name="wishlist_id")
    private Long id;

    @MapsId
    @OneToOne(mappedBy = "wishlist")
    @JoinColumn(name = "wishlist_id")
    private AppUser appUser;


    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "wishlist_ad",
        joinColumns = @JoinColumn(name = "wishlist_id"),
        inverseJoinColumns = @JoinColumn(name = "ad_id")
    )
    private Set<Ad> ads = new HashSet<>();


    public Wishlist(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getAppUser() {
        return appUser.getEmail();
    }


    public void addAd(Ad ad) {
        ads.add(ad);
        ad.getWishlists().add(this);
    }

    public void removeAd(Ad ad) {
        ads.remove(ad);
        ad.getWishlists().remove(this);
    }


}
