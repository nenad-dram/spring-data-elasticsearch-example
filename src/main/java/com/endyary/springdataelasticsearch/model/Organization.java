package com.endyary.springdataelasticsearch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Organization {

    private Long id;
    private String name;
    private String address;

    public Organization() {
    }

    public Organization(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
