package com.example.carsalesserver.AppUser.utils;

import com.example.carsalesserver.Ad.Ad;
import com.example.carsalesserver.Wishlist.Wishlist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppUserDAO {
    private String firstName;
    private String lastName;
    private String userName;
    private List<Ad> ads;
    private Wishlist wishlist;
    private Object role;

}
