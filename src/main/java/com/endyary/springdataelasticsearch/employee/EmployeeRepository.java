package com.endyary.springdataelasticsearch.employee;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, Long> {

    List<Employee> findByOrganizationName(String name);

    List<Employee> findByName(String name);
}
