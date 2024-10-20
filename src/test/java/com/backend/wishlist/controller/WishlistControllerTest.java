package com.backend.wishlist.controller;

import static com.backend.wishlist.controller.WishlistControllerFixtures.makeWishlistCreateDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.backend.wishlist.dto.WishlistCreateDto;
import com.backend.wishlist.service.WishlistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = WishlistController.class)
class WishlistControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private WishlistService wishlistService;

  @Test
  void givenAPayloadOfWishlistCreateDtoJsonWhenCreateProductThenRegisterSuccessfully()
      throws Exception {

    String wishlistCreateJson = objectMapper.writeValueAsString(
        makeWishlistCreateDto());

    ResultActions result = mockMvc.perform(post("/v1/wishlists")
        .contentType(MediaType.APPLICATION_JSON)
        .content(wishlistCreateJson));

    result.andExpect(status().isCreated());
  }

  @Test
  void givenAPayloadOfWishlistCreateDtoWithIdProductNullWhenCreateProductThenResultInAnError()
      throws Exception {
    String wishlistCreateJson = objectMapper.writeValueAsString(
        makeWishlistCreateDto().toBuilder().idProduct(null).build());

    mockMvc.perform(post("/v1/wishlists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(wishlistCreateJson))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.idProduct", Is.is("The idProduct is required.")))
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON));

  }

  @Test
  void givenAPayloadOfWishlistCreateDtoWithIdProductEmptyWhenCreateProductThenResultInAnError()
      throws Exception {
    String wishlistCreateJson = objectMapper.writeValueAsString(
        makeWishlistCreateDto().toBuilder().idProduct("").build());

    mockMvc.perform(post("/v1/wishlists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(wishlistCreateJson))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.idProduct", Is.is("The idProduct is required.")))
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON));

  }

  @Test
  void givenAPayloadOfWishlistCreateDtoWithIdClientNullWhenCreateProductThenResultInAnError()
      throws Exception {
    String wishlistCreateJson = objectMapper.writeValueAsString(
        makeWishlistCreateDto().toBuilder().idClient(null).build());

    mockMvc.perform(post("/v1/wishlists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(wishlistCreateJson))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.idClient", Is.is("The idClient is required.")))
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON));

  }

  @Test
  void givenAPayloadOfWishlistCreateDtoWithIdClientEmptyWhenCreateProductThenResultInAnError()
      throws Exception {
    String wishlistCreateJson = objectMapper.writeValueAsString(
        makeWishlistCreateDto().toBuilder().idClient("").build());

    mockMvc.perform(post("/v1/wishlists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(wishlistCreateJson))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.idClient", Is.is("The idClient is required.")))
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON));

  }

  @Test
  void givenAPayloadOfWishlistCreateDtoWithAmountNullWhenCreateProductThenResultInAnError()
      throws Exception {
    String wishlistCreateJson = objectMapper.writeValueAsString(
        makeWishlistCreateDto().toBuilder().amount(null).build());

    mockMvc.perform(post("/v1/wishlists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(wishlistCreateJson))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.amount", Is.is("The amount is required.")))
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON));

  }

}
