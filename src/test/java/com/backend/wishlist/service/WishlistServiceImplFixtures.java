package com.backend.wishlist.service;

import com.backend.wishlist.domain.WishlistDomain;
import com.backend.wishlist.dto.WishlistCreateDto;

public class WishlistServiceImplFixtures {

  static String ID_CLIENT = "5a4869fa-5f57-4b50-a856-e513bcd23cd2",
      ID_PRODUCT = "97100db9-d2a3-4969-8a55-325201ee8cd3",
      ID_WISHLIST = "538b4e37-6468-44ba-838b-3bd746f23db6";

  static WishlistCreateDto makeWishlistCreateDto() {
    return WishlistCreateDto.builder()
        .idProduct("ccf2c7fa-b401-4cea-8250-568a85de528e")
        .idClient("6ceebbf2-af8b-4670-a529-a93105526d9e")
        .amount(2)
        .build();
  }

  static WishlistDomain makeWishlistDomain() {
    return WishlistDomain.builder()
        .idProduct("ccf2c7fa-b401-4cea-8250-568a85de528e")
        .idClient("6ceebbf2-af8b-4670-a529-a93105526d9e")
        .amount(2)
        .build();
  }

}
