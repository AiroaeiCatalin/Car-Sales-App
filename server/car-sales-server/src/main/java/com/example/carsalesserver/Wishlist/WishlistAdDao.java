package com.example.carsalesserver.Wishlist;

import com.example.carsalesserver.Ad.Ad;
import com.example.carsalesserver.AppUser.utils.AppUserDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class WishlistAdDao {
    private Long adId;
    private String userName;
}
