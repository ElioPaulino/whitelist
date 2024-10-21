package com.backend.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductWishlistDto {

  private String idWishlist;
  private String idProduct;
  private String idCustomer;
  private int amount;
}
