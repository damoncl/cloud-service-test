package com.damon.test.controller;

import com.damon.test.webflux.domain.City;
import com.damon.test.webflux.handler.CityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/city")
public class WebFluxController {

    @Autowired
    private CityHandler cityHandler;

    @GetMapping("/{id}")
    public Mono<City> findById(@PathVariable("id") Long id) {
        return cityHandler.findById(id);
    }

    @GetMapping("/list")
    public Flux<City> findAll() {
        return cityHandler.findAll();
    }

    @PostMapping("/insert")
    public Mono<Long> insert(@RequestBody City city) {
        return cityHandler.saveCity(city);
    }

    @PostMapping("/update")
    public Mono<Long> modify(@RequestBody City city) {
        return cityHandler.modify(city);
    }

    @DeleteMapping("/{id}")
    public Mono<Long> delete(@PathVariable("id") Long id) {
        return cityHandler.delete(id);
    }
}
