package com.backend.wishlist.repository;

import com.backend.wishlist.domain.WishlistDomain;
import java.util.List;
import java.util.Optional;

public interface WishlistRepository {

  WishlistDomain save(WishlistDomain wishlist);

  void deleteProduct(String idProduct, String idCustomer);

  List<WishlistDomain> findProductsByIdCustomer(String idCustomer);

  long countProductsByIdCustomer(String idCustomer);

  Optional<WishlistDomain> findProductByIdProductAndIdCustomer(String idProduct, String idCustomer);
}
