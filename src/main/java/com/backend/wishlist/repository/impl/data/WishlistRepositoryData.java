package com.backend.wishlist.repository.impl.data;

import com.backend.wishlist.domain.WishlistDomain;
import com.backend.wishlist.dto.ProductWishlistDto;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WishlistRepositoryData extends MongoRepository<WishlistDomain, String> {

  @Transactional
  void deleteByIdProductAndIdClient(String idProduct, String idClient);

  List<WishlistDomain> findAllByIdClient(String idClient);

  Optional<WishlistDomain> findByIdProductAndIdClient(String idProduct, String idClient);

  long countByIdClient(String idClient);
}
