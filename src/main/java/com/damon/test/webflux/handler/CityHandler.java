package com.damon.test.webflux.handler;

import com.damon.test.webflux.domain.City;
import com.damon.test.webflux.repo.CityRepository;
import com.damon.test.webflux.repo.ReactiveCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityHandler {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ReactiveCityRepository reactiveCityRepository;

    public Mono<Long> saveCity(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.save(city)));
    }

    public Mono<City> findById(Long id) {
        return Mono.justOrEmpty(cityRepository.findCityById(id));
    }

    public Flux<City> findAll() {
        return Flux.fromIterable(cityRepository.finaAll());
    }

    public Mono<Long> modify(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.update(city)));
    }

    public Mono<Long> delete(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.delete(id)));
    }
    //reactive

    public Mono<City> reactiveSaveCity(City city) {
        return reactiveCityRepository.save(city);
    }

    public Mono<City> reactiveFindById(Long id) {
        return reactiveCityRepository.findById(id);
    }

    public Flux<City> reactiveFindAll() {
        return reactiveCityRepository.findAll();
    }

    public Mono<City> reactiveModify(City city) {
        return reactiveCityRepository.save(city);
    }

    public Mono<Long> reactiveDelete(Long id) {
        reactiveCityRepository.deleteById(id);
        return Mono.create(cityMonSink -> cityMonSink.success(id));
    }
}
