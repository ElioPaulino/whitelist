package com.backend.wishlist.service;

import com.backend.wishlist.dto.ProductWishlistDto;
import com.backend.wishlist.dto.WishlistCreateDto;
import com.backend.wishlist.exception.CustomDataRuntimeExceptionException;
import java.util.List;

public interface WishlistService {

  String createProduct(WishlistCreateDto wishlistCreate) throws CustomDataRuntimeExceptionException;

  void deleteProduct(String idProduct, String idClient);

  List<ProductWishlistDto> findProductsByIdClient(String idClient);

  ProductWishlistDto findProductByIdProductAndIdClient(String idProduct, String idClient);
}
