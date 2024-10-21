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
    long quantityProducts = wishlistRepository.countProductsByIdClient(
        wishlistCreate.getIdClient());

    if (quantityProducts >= 20) {
      throw new CustomDataRuntimeExceptionException("Product limit reached.");
    }

    WishlistDomain wishlist = WishlistDomain.builder()
        .id(UUID.randomUUID().toString())
        .idClient(wishlistCreate.getIdClient())
        .idProduct(wishlistCreate.getIdProduct())
        .amount(wishlistCreate.getAmount())
        .build();

    WishlistDomain wishlistDomain = wishlistRepository.save(wishlist);
    return wishlistDomain.getId();
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void deleteProductWishlist(String idProduct, String idClient) {
    wishlistRepository.deleteProduct(idProduct, idClient);
  }

  @Override
  public List<ProductWishlistDto> findProductsByIdClient(String idClient) {
    List<WishlistDomain> wishlist = wishlistRepository.findProductsByIdClient(idClient);
    List<ProductWishlistDto> products = wishlist.stream().map(item -> {
      return ProductWishlistDto.builder()
          .idWishlist(item.getId())
          .amount(item.getAmount())
          .idProduct(item.getIdProduct())
          .idClient(item.getIdClient())
          .build();
    }).collect(Collectors.toList());
    return products;
  }

  @Override
  public ProductWishlistDto findProductByIdProductAndIdClient(String idProduct, String idClient) {
    Optional<WishlistDomain> wishlist = wishlistRepository.findProductByIdProductAndIdClient(
        idProduct, idClient);
    wishlist.orElseThrow(() -> new CustomDataNotFoundException("Product not found."));

    AtomicReference<ProductWishlistDto> productWishlistDto = new AtomicReference<>(
        new ProductWishlistDto());
    wishlist.ifPresent(item -> {
      productWishlistDto.set(ProductWishlistDto.builder()
          .idWishlist(item.getId())
          .amount(item.getAmount())
          .idProduct(item.getIdProduct())
          .idClient(item.getIdClient())
          .build());
    });

    return productWishlistDto.get();
  }
}
