package com.backend.wishlist.repository.impl;

import com.backend.wishlist.domain.WishlistDomain;
import com.backend.wishlist.repository.WishlistRepository;
import com.backend.wishlist.repository.impl.data.WishlistRepositoryData;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WishlistRepositoryImpl implements WishlistRepository {

  private final WishlistRepositoryData wishlistRepositoryData;

  @Override
  public WishlistDomain save(WishlistDomain wishlist) {
    return wishlistRepositoryData.save(wishlist);
  }

  @Override
  public void deleteProduct(String idProduct, String idCustomer) {
    wishlistRepositoryData.deleteByIdProductAndIdCustomer(idProduct, idCustomer);
  }

  @Override
  public List<WishlistDomain> findProductsByIdCustomer(String idCustomer) {
    return wishlistRepositoryData.findAllByIdCustomer(idCustomer);
  }

  @Override
  public long countProductsByIdCustomer(String idCustomer) {
    return wishlistRepositoryData.countByIdCustomer(idCustomer);
  }

  @Override
  public Optional<WishlistDomain> findProductByIdProductAndIdCustomer(String idProduct,
      String idCustomer) {
    return wishlistRepositoryData.findByIdProductAndIdCustomer(idProduct, idCustomer);
  }
}
