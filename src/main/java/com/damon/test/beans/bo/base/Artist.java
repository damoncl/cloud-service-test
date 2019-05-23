package com.damon.test.beans.bo.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Artist {
    private String name;
    private String origin;
    private List<String> members;

    public Artist() {}

    public Artist(String name, String origin) {
        this.name = name;
        this.origin = origin;
    }
}
