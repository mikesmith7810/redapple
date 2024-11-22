package com.mike.webflux.service;

import com.mike.webflux.model.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FruitService {

  @Autowired private ReactiveMongoTemplate reactiveMongoTemplate;

  public Mono<Fruit> findById(String id) {
    return reactiveMongoTemplate.findById(id, Fruit.class);
  }

  public Flux<Fruit> findAll() {
    return reactiveMongoTemplate.findAll(Fruit.class);
  }

  public Mono<Fruit> save(Fruit fruit) {
    return reactiveMongoTemplate.save(fruit);
  }
}
