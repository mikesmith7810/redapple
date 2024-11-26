package com.mike.webflux.handlers;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.mike.webflux.model.Fruit;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FruitHandler {
  private final Validator validator;
  private ReactiveMongoTemplate reactiveMongoTemplate;

  public FruitHandler(ReactiveMongoTemplate reactiveMongoTemplate, Validator validator) {
    this.reactiveMongoTemplate = reactiveMongoTemplate;
    this.validator = validator;
  }

  public Mono<ServerResponse> getFruit(ServerRequest request) {
    return ok().contentType(MediaType.APPLICATION_JSON)
        .body(
            reactiveMongoTemplate.findById(request.pathVariable("name"), Fruit.class), Fruit.class);
  }

  public Mono<ServerResponse> saveFruit(ServerRequest serverRequest) {
    return serverRequest
        .bodyToMono(Fruit.class)
        .flatMap(
            fruit -> {
              Errors errors = new BeanPropertyBindingResult(fruit, "fruit");
              validator.validate(fruit, errors);

              if (errors.hasErrors()) {
                return badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(errors.getAllErrors());
              } else {

                return reactiveMongoTemplate
                    .save(fruit)
                    .flatMap(
                        savedFruit ->
                            ok().contentType(MediaType.APPLICATION_JSON).bodyValue(savedFruit));
              }
            });
  }
}
