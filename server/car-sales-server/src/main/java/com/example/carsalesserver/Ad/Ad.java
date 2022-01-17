package com.example.carsalesserver.Ad;


import com.example.carsalesserver.AppUser.AppUser;
import com.example.carsalesserver.Car.Car;
import com.example.carsalesserver.Wishlist.Wishlist;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Ad {
    @Id
    @Column(name="ad_id")
    @SequenceGenerator(
            name = "ad_sequence",
            sequenceName = "ad_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ad_sequence"
    )

    private Long id;
    private String description;

    private String carImageLink; // S3 key

    //Sa mai vad aici cu equalsu cum a facut amigoscode



//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad", cascade = CascadeType.ALL,
//            orphanRemoval = true)
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    @PrimaryKeyJoinColumn
    private Car car;


//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    @JsonBackReference
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    @ManyToMany(mappedBy = "ads")
    private Set<Wishlist> wishlists = new HashSet<>();

    public String getAppUser() {
        return appUser.getEmail();
    }

    @JsonIgnore
    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setCar(Car car) {
        this.car = car;
        this.car.setAd(this);
    }

    public Optional<String> getCarImageLink() {
        return Optional.ofNullable(carImageLink);
    }
}
