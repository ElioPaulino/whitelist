package com.backend.wishlist.controller;

import com.backend.wishlist.dto.ProductWishlistDto;
import com.backend.wishlist.dto.WishlistCreateDto;
import com.backend.wishlist.exception.CustomDataRuntimeExceptionException;
import com.backend.wishlist.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/wishlists")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Wishlist", description = "Operations related to managing Projects")
public class WishlistController {

  private final WishlistService wishlistService;

  @Operation(summary = "Add product to wishlist")
  @PostMapping
  public ResponseEntity<String> addProductWishlist(
      @Valid @RequestBody WishlistCreateDto wishlistCreate)
      throws CustomDataRuntimeExceptionException {
    String id = wishlistService.addProduct(wishlistCreate);
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @Operation(summary = "Get all products by idCustomer")
  @GetMapping("/customers/{idCustomer}")
  public ResponseEntity<List<ProductWishlistDto>> findProductsByIdCustomer(
      @PathVariable String idCustomer) {
    return ResponseEntity.ok(wishlistService.findProductsByIdCustomer(idCustomer));
  }

  @Operation(summary = "Get product by idProduct and idCustomer")
  @GetMapping("/products/{idProduct}/customers/{idCustomer}")
  public ResponseEntity<ProductWishlistDto> findProductByIdProductAndIdCustomer(
      @PathVariable String idProduct, @PathVariable String idCustomer) {
    return ResponseEntity.ok(
        wishlistService.findProductByIdProductAndIdCustomer(idProduct, idCustomer));
  }

  @Operation(summary = "Delete product by idProduct and idCustomere")
  @DeleteMapping("/products/{idProduct}/customers/{idCustomer}")
  public ResponseEntity<Void> deleteProductWishlist(@PathVariable String idProduct,
      @PathVariable String idCustomer) {
    wishlistService.deleteProductWishlist(idProduct, idCustomer);
    return ResponseEntity.noContent().build();

  }
}
