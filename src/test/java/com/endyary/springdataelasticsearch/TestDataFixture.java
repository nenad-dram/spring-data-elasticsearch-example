package com.endyary.springdataelasticsearch;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import com.endyary.springdataelasticsearch.employee.Employee;
import com.endyary.springdataelasticsearch.model.Organization;

@Component
public class TestDataFixture {

    private static final String EMPLOYEE_INDEX = "employees";

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public void insert() {
        List<Employee> employees = getEmployees();

        List<IndexQuery> queries = employees.stream()
                .map(employee -> new IndexQueryBuilder()
                        .withId(employee.getId().toString())
                        .withObject(employee).build())
                .toList();

        elasticsearchTemplate.bulkIndex(queries, IndexCoordinates.of(EMPLOYEE_INDEX));
    }

    private List<Employee> getEmployees() {
        List<Organization> organizations = getOrganizations();
        Employee employee1 = new Employee(1L, "John Doe", 25, "PositionA", organizations.get(0));
        Employee employee2 = new Employee(2L, "Jane Doe", 30, "PositionB", organizations.get(0));

        Employee employee3 = new Employee(3L, "Bart Mile", 40, "PositionC", organizations.get(1));
        Employee employee4 = new Employee(4L, "Ann Smith", 35, "PositionD", organizations.get(1));

        return Arrays.asList(employee1, employee2, employee3, employee4);
    }

    private List<Organization> getOrganizations() {
        Organization organization1 = new Organization(1L, "Organization1", "Org Street No. 1");
        Organization organization2 = new Organization(2L, "Organization2", "Org Street No. 2");

        return Arrays.asList(organization1, organization2);
    }
}
