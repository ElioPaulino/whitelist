package com.backend.wishlist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.backend.wishlist.repository.impl.data")
public class MongoConfiguration {

}
