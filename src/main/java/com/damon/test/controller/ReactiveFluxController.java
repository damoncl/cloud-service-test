package com.damon.test.controller;

import com.damon.test.webflux.domain.City;
import com.damon.test.webflux.handler.CityHandler;
import com.damon.test.webflux.repo.ReactiveCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactiveCity")
public class ReactiveFluxController {

    @Autowired
    private CityHandler reactiveCityRepository;

    @GetMapping("/{id}")
    public Mono<City> findById(@PathVariable("id") Long id) {
        return reactiveCityRepository.reactiveFindById(id);
    }

    @GetMapping("/list")
    public Flux<City> findAll() {
        return reactiveCityRepository.findAll();
    }

    @PostMapping("/insert")
    public Mono<City> insert(@RequestBody City city) {
        return reactiveCityRepository.reactiveSaveCity(city);
    }

    @PostMapping("/update")
    public Mono<City> modify(@RequestBody City city) {
        return reactiveCityRepository.reactiveSaveCity(city);
    }

    @DeleteMapping("/{id}")
    public Mono<Long> delete(@PathVariable("id") Long id) {
        return reactiveCityRepository.reactiveDelete(id);
    }
}
