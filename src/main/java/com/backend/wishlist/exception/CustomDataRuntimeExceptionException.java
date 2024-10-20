package com.backend.wishlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomDataRuntimeExceptionException extends Exception {

  public CustomDataRuntimeExceptionException() {
    super();
  }
  public CustomDataRuntimeExceptionException(String message) {
    super(message);
  }
}
