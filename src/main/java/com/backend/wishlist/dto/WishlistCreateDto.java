package com.backend.wishlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class WishlistCreateDto {

  @NotBlank(message = "The idClient is required.")
  private String idClient;

  @NotBlank(message = "The idProduct is required.")
  private String idProduct;

  @NotNull(message = "The amount is required.")
  private int amount;
}
