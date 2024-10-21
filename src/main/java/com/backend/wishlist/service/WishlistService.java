package com.backend.wishlist.service;

import com.backend.wishlist.dto.ProductWishlistDto;
import com.backend.wishlist.dto.WishlistCreateDto;
import com.backend.wishlist.exception.CustomDataRuntimeExceptionException;
import java.util.List;

public interface WishlistService {

  String addProduct(WishlistCreateDto wishlistCreate) throws CustomDataRuntimeExceptionException;

  void deleteProductWishlist(String idProduct, String idClient);

  List<ProductWishlistDto> findProductsByIdClient(String idClient);

  ProductWishlistDto findProductByIdProductAndIdClient(String idProduct, String idClient);
}
