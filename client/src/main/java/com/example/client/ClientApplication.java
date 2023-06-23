package com.example.client;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb.routes().route(rs -> rs.path("/proxy")  // localhost:9999/proxy
                .filters(fs -> fs.setPath("/customers").retry(10).addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")).uri("http://localhost:8080/customers") // localhost:8080/proxy/customers
        ).build();
    }

    @Bean
    CustomerHttpClient customerHttpClient(WebClient.Builder b) {
        var wc = b.baseUrl("http://localhost:8080").build();
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(wc))
                .build()
                .createClient(CustomerHttpClient.class);
    }

    @Bean
    ApplicationRunner applicationRunner(CustomerHttpClient http) {
        return a -> http.customers().subscribe(System.out::println);
    }

}

@Controller
class CustomerGraphqlController {

    private final CustomerHttpClient http;

    CustomerGraphqlController(CustomerHttpClient http) {
        this.http = http;
    }


    @BatchMapping
    Map<Customer, Profile> profile(List<Customer> customerList) {
        var map = new HashMap<Customer, Profile>();
        for (var c : customerList)
            map.put(c, new Profile(c.id()));
        return map;
    }

    @QueryMapping
//    @SchemaMapping(typeName = "Query", field = "customersByName")
    Flux<Customer> customersByName(@Argument String name) {
        return this.http.customersByName(name);
    }

    @QueryMapping
        // @SchemaMapping(typeName = "Query", field = "customers")
    Flux<Customer> customers() {
        return this.http.customers();
    }
}

record Profile(Integer id) {
}

interface CustomerHttpClient {

    @GetExchange("/customers")
    Flux<Customer> customers();

    @GetExchange("/customers/{name}")
    Flux<Customer> customersByName(@PathVariable String name);
}


// look ma, no Lombok!
record Customer(Integer id, String name) {
}