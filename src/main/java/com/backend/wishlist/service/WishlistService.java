package com.backend.wishlist.service;

import com.backend.wishlist.dto.ProductWishlistDto;
import com.backend.wishlist.dto.WishlistCreateDto;
import com.backend.wishlist.exception.CustomDataRuntimeExceptionException;
import java.util.List;

public interface WishlistService {

  String addProduct(WishlistCreateDto wishlistCreate) throws CustomDataRuntimeExceptionException;

  void deleteProductWishlist(String idProduct, String idCustomer);

  List<ProductWishlistDto> findProductsByIdCustomer(String idCustomer);

  ProductWishlistDto findProductByIdProductAndIdCustomer(String idProduct, String idCustomer);
}
