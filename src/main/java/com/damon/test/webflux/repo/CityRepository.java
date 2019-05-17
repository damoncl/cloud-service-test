package com.damon.test.webflux.repo;

import com.damon.test.webflux.domain.City;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CityRepository {

    private Map<Long, City> repository = new ConcurrentHashMap<>();
    private final AtomicLong atomicLong = new AtomicLong(0);

    public Long save(City city) {
        Long id = atomicLong.incrementAndGet();
        city.setId(id);
        repository.put(id, city);
        return id;
    }

    public Collection<City> finaAll() {
        return repository.values();
    }

    public City findCityById(Long id) {
        return repository.get(id);
    }

    public Long update(City city) {
        repository.put(city.getId(), city);
        return city.getId();
    }

    public Long delete(Long id) {
        repository.remove(id);
        return id;
    }
}
