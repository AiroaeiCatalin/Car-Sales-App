package com.example.carsalesserver.AppUser;

import com.example.carsalesserver.Ad.Ad;
import com.example.carsalesserver.AppUser.utils.AppUserRole;
import com.example.carsalesserver.Car.Car;
import com.example.carsalesserver.Wishlist.Wishlist;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "appuser_sequence",
            sequenceName = "appuser_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appuser_sequence"
    )

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Ad> ads;
//    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Wishlist wishlist;
    //ar mai trb poza de user, detalii user


    public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.wishlist = new Wishlist(this);


//        this.phoneNumber = phoneNumber;
        this.appUserRole = appUserRole;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

//    public void setWishlist(Wishlist wishlist) {
//        this.wishlist = wishlist;
//        this.wishlist.setAppUser(this);
//    }
}
