package com.backend.wishlist.repository;

import com.backend.wishlist.domain.WishlistDomain;
import java.util.List;
import java.util.Optional;

public interface WishlistRepository {

  WishlistDomain save(WishlistDomain wishlist);

  void deleteProduct(String idProduct, String idClient);

  List<WishlistDomain> findProductsByIdClient(String idClient);

  long countProductsByIdClient(String idClient);

  Optional<WishlistDomain> findProductByIdProductAndIdClient(String idProduct, String idClient);
}
