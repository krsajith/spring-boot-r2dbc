package com.example.demo;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CompanyController {

    @QueryMapping
    public Flux<Company> companies() {
        return Flux.just(new Company(1L, "Company A"), new Company(2L, "Company B"));
    }

}

record Company(Long id, String name) {

}
