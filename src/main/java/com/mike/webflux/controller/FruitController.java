package com.mike.webflux.controller;

import com.mike.webflux.model.Fruit;
import com.mike.webflux.service.FruitService;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// @RestController
@RequestMapping("fruit")
public class FruitController {

  @Autowired private FruitService fruitService;

  @GetMapping("/{name}")
  public Mono<Fruit> getFruitById(@PathVariable String name) {
    return fruitService.findById(name);
  }

  @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Fruit> getAllFruit() {
    return fruitService.findAll().delayElements(Duration.ofSeconds(1));
  }

  @PostMapping("/")
  public Mono<Fruit> saveFruit(@RequestBody Fruit fruit) {
    return fruitService.save(fruit);
  }
}
