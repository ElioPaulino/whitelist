package com.backend.wishlist.controller;

import com.backend.wishlist.dto.WishlistCreateDto;

public class WishlistControllerFixtures {

  static WishlistCreateDto makeWishlistCreateDto() {
    return WishlistCreateDto.builder()
        .idProduct("ccf2c7fa-b401-4cea-8250-568a85de528e")
        .idCustomer("6ceebbf2-af8b-4670-a529-a93105526d9e")
        .amount(2)
        .build();
  }

}
