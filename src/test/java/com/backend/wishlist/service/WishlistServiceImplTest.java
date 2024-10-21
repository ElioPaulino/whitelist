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
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
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
    when(wishlistRepository.countProductsByIdCustomer(wishlistCreate.getIdCustomer())).thenReturn(
        10L);
    when(wishlistRepository.save(any())).thenReturn(
        wishlistDomain.toBuilder().id(ID_WISHLIST).build());

    Assert.assertEquals(ID_WISHLIST, getSut().addProduct(wishlistCreate));

    verify(wishlistRepository, times(1)).save(any());
  }

  @Test
  public void givenWishlistCreateDtoWhenCreateWithProductsLimitReachedFromCustomerThenShouldError() {
    WishlistCreateDto wishlistCreate = makeWishlistCreateDto();
    when(wishlistRepository.countProductsByIdCustomer(wishlistCreate.getIdCustomer())).thenReturn(
        20L);

    assertThatThrownBy(() -> getSut().addProduct(wishlistCreate))
        .isInstanceOf(CustomDataRuntimeExceptionException.class)
        .hasMessage("Product limit reached.");

    verify(wishlistRepository, times(0)).save(any());
  }

  @Test
  public void givenIdProductAndIdCustomerWhenFindProductDoesNotExistThenShouldError()
      throws CustomDataNotFoundException {
    Optional<WishlistDomain> wishlist = Optional.empty();
    when(
        wishlistRepository.findProductByIdProductAndIdCustomer(ID_PRODUCT,
            ID_CUSTOMER)).thenReturn(wishlist);

    assertThatThrownBy(
        () -> getSut().findProductByIdProductAndIdCustomer(ID_PRODUCT,
            ID_CUSTOMER))
        .isInstanceOf(CustomDataNotFoundException.class)
        .hasMessage("Product not found.");
  }

  @Test
  public void givenIdProductAndIdCustomerWhenFindProductThenShouldReturnProductWishlistDto()
      throws CustomDataNotFoundException {
    Optional<WishlistDomain> wishlist = Optional.of(makeWishlistDomain().toBuilder()
        .id("5a4869fa-5f57-4b50-a856-e513bcd23cd2")
        .build());
    when(
        wishlistRepository.findProductByIdProductAndIdCustomer(ID_PRODUCT,
            ID_CUSTOMER)).thenReturn(wishlist);
    Assert.assertEquals(makeProductWishlistDto(),
        getSut().findProductByIdProductAndIdCustomer(ID_PRODUCT, ID_CUSTOMER));

  }

  @Test
  public void givenIdCustomerWhenFindProductsThenShouldReturnListOfProductWishlistDto()
      throws CustomDataNotFoundException {
    List<WishlistDomain> wishlist = makeWishlistDomainList();
    when(
        wishlistRepository.findProductsByIdCustomer(ID_CUSTOMER)).thenReturn(wishlist);
    Assert.assertEquals(makeProductWishlistDtoList(),
        getSut().findProductsByIdCustomer(ID_CUSTOMER));
  }

  @Test
  public void givenIdProductAndIdCustomerWhenDeleteProductWishListThenShouldRegisterSuccessfully() {
    getSut().deleteProductWishlist(ID_PRODUCT, ID_CUSTOMER);
    verify(wishlistRepository, times(1)).deleteProduct(ID_PRODUCT, ID_CUSTOMER);
  }


}
