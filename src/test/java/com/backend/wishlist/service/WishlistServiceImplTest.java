package com.backend.wishlist.service;

import static com.backend.wishlist.service.WishlistServiceImplFixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.backend.wishlist.domain.WishlistDomain;
import com.backend.wishlist.dto.WishlistCreateDto;
import com.backend.wishlist.exception.CustomDataNotFoundException;
import com.backend.wishlist.exception.CustomDataRuntimeExceptionException;
import com.backend.wishlist.repository.WishlistRepository;
import com.backend.wishlist.service.impl.WishlistServiceImpl;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WishlistServiceImplTest {

  @Mock
  private WishlistRepository wishlistRepository;

  private WishlistService getSut() {
    return new WishlistServiceImpl(wishlistRepository);
  }

  @Test
  public void givenWishlistCreateDtoWhenCreateThenShouldRegisterSuccessfully()
      throws CustomDataRuntimeExceptionException {
    WishlistCreateDto wishlistCreate = makeWishlistCreateDto();
    WishlistDomain wishlistDomain = makeWishlistDomain();
    when(wishlistRepository.countProductsByIdClient(wishlistCreate.getIdClient())).thenReturn(10L);
    when(wishlistRepository.save(any())).thenReturn(
        wishlistDomain.toBuilder().id(ID_WISHLIST).build());

    getSut().createProduct(wishlistCreate);

    verify(wishlistRepository, times(1)).save(any());
  }

  @Test
  public void givenWishlistCreateDtoWhenCreateWithProductsLimitReachedFromClientThenShouldError() {
    WishlistCreateDto wishlistCreate = makeWishlistCreateDto();
    when(wishlistRepository.countProductsByIdClient(wishlistCreate.getIdClient())).thenReturn(20L);

    assertThatThrownBy(() -> getSut().createProduct(wishlistCreate))
        .isInstanceOf(CustomDataRuntimeExceptionException.class)
        .hasMessage("Product limit reached.");

    verify(wishlistRepository, times(0)).save(any());
  }

  @Test
  public void givenIdProductAndIdClientWhenFindProductDoesNotExistThenShouldError()
      throws CustomDataNotFoundException {
    Optional<WishlistDomain> wishlist = Optional.empty();
    when(
        wishlistRepository.findProductByIdProductAndIdClient(ID_PRODUCT,
            ID_CLIENT)).thenReturn(wishlist);

    assertThatThrownBy(
        () -> getSut().findProductByIdProductAndIdClient(ID_PRODUCT,
            ID_CLIENT))
        .isInstanceOf(CustomDataNotFoundException.class)
        .hasMessage("Product not found.");
  }

  @Test
  public void givenIdProductAndIdClientWhenDeleteProductThenShouldError()
      throws CustomDataNotFoundException {
    Optional<WishlistDomain> wishlist = Optional.empty();
    when(
        wishlistRepository.findProductByIdProductAndIdClient(ID_PRODUCT,
            ID_CLIENT)).thenReturn(wishlist);

    assertThatThrownBy(
        () -> getSut().deleteProduct(ID_PRODUCT,
            ID_CLIENT))
        .isInstanceOf(CustomDataNotFoundException.class)
        .hasMessage("Product not found.");
  }

}
