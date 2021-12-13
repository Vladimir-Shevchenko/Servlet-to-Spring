package com.FunctionalEndpoint;


import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Collection;
import java.util.Collections;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Component
public class FunctionalEndpoint {
    @Bean
   public RouterFunction<ServerResponse> routerFunction(){
        return route().GET("/hello", this::getInfo).build();
    }

    public ServerResponse getInfo(ServerRequest serverRequest ) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                Collections.singletonMap("akey",serverRequest.param("value").orElse("")));

    }
}
