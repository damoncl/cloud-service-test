package com.damon.test.webflux.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class City {
    @Id
    private Long id;
    private Long provinceId;
    private String cityName;
    private String description;
}
