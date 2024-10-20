package com.backend.wishlist;

import com.backend.wishlist.controller.WishlistController;
import com.backend.wishlist.repository.impl.data.WishlistRepositoryData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class WishlistApplication {

  public static void main(String[] args) {
    SpringApplication.run(WishlistApplication.class, args);
  }

}
