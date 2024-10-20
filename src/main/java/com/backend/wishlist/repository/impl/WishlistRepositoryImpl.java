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
  public void deleteProduct(String idProduct, String idClient) {
    wishlistRepositoryData.deleteByIdProductAndIdClient(idProduct, idClient);
  }

  @Override
  public List<WishlistDomain> findProductsByIdClient(String idClient) {
    return wishlistRepositoryData.findAllByIdClient(idClient);
  }

  @Override
  public long countProductsByIdClient(String idClient) {
    return wishlistRepositoryData.countByIdClient(idClient);
  }

  @Override
  public Optional<WishlistDomain> findProductByIdProductAndIdClient(String idProduct,
      String idClient) {
    return wishlistRepositoryData.findByIdProductAndIdClient(idProduct, idClient);
  }
}
