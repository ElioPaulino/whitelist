package com.backend.wishlist.service.impl;

import com.backend.wishlist.domain.WishlistDomain;
import com.backend.wishlist.dto.ProductWishlistDto;
import com.backend.wishlist.dto.WishlistCreateDto;
import com.backend.wishlist.exception.CustomDataNotFoundException;
import com.backend.wishlist.exception.CustomDataRuntimeExceptionException;
import com.backend.wishlist.repository.WishlistRepository;
import com.backend.wishlist.service.WishlistService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

  private final WishlistRepository wishlistRepository;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public String addProduct(WishlistCreateDto wishlistCreate)
      throws CustomDataRuntimeExceptionException {
    long quantityProducts = wishlistRepository.countProductsByIdCustomer(
        wishlistCreate.getIdCustomer());

    if (quantityProducts >= 20) {
      throw new CustomDataRuntimeExceptionException("Product limit reached.");
    }

    WishlistDomain wishlist = WishlistDomain.builder()
        .id(UUID.randomUUID().toString())
        .idCustomer(wishlistCreate.getIdCustomer())
        .idProduct(wishlistCreate.getIdProduct())
        .amount(wishlistCreate.getAmount())
        .build();

    WishlistDomain wishlistDomain = wishlistRepository.save(wishlist);
    return wishlistDomain.getId();
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void deleteProductWishlist(String idProduct, String idCustomer) {
    wishlistRepository.deleteProduct(idProduct, idCustomer);
  }

  @Override
  public List<ProductWishlistDto> findProductsByIdCustomer(String idCustomer) {
    List<WishlistDomain> wishlist = wishlistRepository.findProductsByIdCustomer(idCustomer);
    List<ProductWishlistDto> products = wishlist.stream().map(item -> {
      return ProductWishlistDto.builder()
          .idWishlist(item.getId())
          .amount(item.getAmount())
          .idProduct(item.getIdProduct())
          .idCustomer(item.getIdCustomer())
          .build();
    }).collect(Collectors.toList());
    return products;
  }

  @Override
  public ProductWishlistDto findProductByIdProductAndIdCustomer(String idProduct,
      String idCustomer) {
    Optional<WishlistDomain> wishlist = wishlistRepository.findProductByIdProductAndIdCustomer(
        idProduct, idCustomer);
    wishlist.orElseThrow(() -> new CustomDataNotFoundException("Product not found."));

    AtomicReference<ProductWishlistDto> productWishlistDto = new AtomicReference<>(
        new ProductWishlistDto());
    wishlist.ifPresent(item -> {
      productWishlistDto.set(ProductWishlistDto.builder()
          .idWishlist(item.getId())
          .amount(item.getAmount())
          .idProduct(item.getIdProduct())
          .idCustomer(item.getIdCustomer())
          .build());
    });

    return productWishlistDto.get();
  }
}
