package com.backend.wishlist.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wishlist")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class WishlistDomain {


  @Id
  private String id;
  private String idClient;
  private String idProduct;
  private int amount;

}
