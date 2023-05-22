package br.com.scheronlini.apigatewayadm.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Predicate;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("hello", "world"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/fornecedores/**")
                        .uri("lb://cadastro-fornecedor/**"))
                .route(p -> p.path("/enderecos/**")
                        .uri("lb://enderecos/**"))
                .build();
    }
}
