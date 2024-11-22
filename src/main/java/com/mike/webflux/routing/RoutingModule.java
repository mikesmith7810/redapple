package com.mike.webflux.routing;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RoutingModule {

  @Bean
  public RouterFunction<ServerResponse> routeHelloWorld() {
    return route(
        GET("/hello"),
        request ->
            ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Hello Mike, Reactive World!"), String.class));
  }
}
