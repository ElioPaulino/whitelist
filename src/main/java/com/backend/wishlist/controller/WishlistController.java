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
  public ResponseEntity<String> addProductWishlist(@Valid @RequestBody WishlistCreateDto wishlistCreate)
      throws CustomDataRuntimeExceptionException {
    String id = wishlistService.createProduct(wishlistCreate);
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @Operation(summary = "Get all products by idClient")
  @GetMapping("/clients/{idClient}")
  public ResponseEntity<List<ProductWishlistDto>> findProductsByIdClient(@PathVariable String idClient) {
    return ResponseEntity.ok(wishlistService.findProductsByIdClient(idClient));
  }

  @Operation(summary = "Get product by idProduct and idClient")
  @GetMapping("/products/{idProduct}/clients/{idClient}")
  public ResponseEntity<ProductWishlistDto> findProductByIdProductAndIdClient(@PathVariable String idProduct, @PathVariable String idClient) {
    return ResponseEntity.ok(wishlistService.findProductByIdProductAndIdClient(idProduct, idClient));
  }

  @Operation(summary = "Delete product by idProduct and idCliente")
  @DeleteMapping("/products/{idProduct}/clients/{idClient}")
  public ResponseEntity<Void> deleteProduct(@PathVariable String idProduct, @PathVariable String idClient) {
    wishlistService.deleteProduct(idProduct, idClient);
    return ResponseEntity.noContent().build();
  }
}