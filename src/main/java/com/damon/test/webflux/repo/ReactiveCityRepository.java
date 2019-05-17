package com.damon.test.webflux.repo;

import com.damon.test.webflux.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveCityRepository extends ReactiveMongoRepository<City, Long> {
}
