package com.mike.webflux.routing;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.mike.webflux.handlers.FruitHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class FruitModule {

  private final FruitHandler fruitHandler;

  public FruitModule(FruitHandler fruitHandler) {
    this.fruitHandler = fruitHandler;
  }

  @Bean
  public RouterFunction<ServerResponse> fruitRoutes() {
    return route()
        .GET("/fruit/{name}", fruitHandler::getFruit)
        .GET("/fruit", fruitHandler::getAllFruit)
        .POST("/fruit", fruitHandler::saveFruit)
        .build();
  }
}
