package com.backend.wishlist.repository.impl.data;

import com.backend.wishlist.domain.WishlistDomain;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WishlistRepositoryData extends MongoRepository<WishlistDomain, String> {


  void deleteByIdProductAndIdCustomer(String idProduct, String idCustomer);

  List<WishlistDomain> findAllByIdCustomer(String idCustomer);

  Optional<WishlistDomain> findByIdProductAndIdCustomer(String idProduct, String idCustomer);

  long countByIdCustomer(String idCustomer);
}
