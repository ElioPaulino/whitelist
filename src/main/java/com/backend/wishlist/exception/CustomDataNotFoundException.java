package com.backend.wishlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CustomDataNotFoundException extends RuntimeException {


  public CustomDataNotFoundException() {
    super();
  }

  public CustomDataNotFoundException(String message) {
    super(message);
  }
}
