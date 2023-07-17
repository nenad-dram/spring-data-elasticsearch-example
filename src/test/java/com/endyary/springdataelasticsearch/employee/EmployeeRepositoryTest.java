package com.endyary.springdataelasticsearch.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.endyary.springdataelasticsearch.TestDataFixture;
import com.endyary.springdataelasticsearch.model.Organization;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TestDataFixture testDataFixture;

    @BeforeTestClass
    public void setup() {
        employeeRepository.deleteAll();
        testDataFixture.insert();
    }

    @Test
    void addNew() {
        Employee employee = new Employee();
        employee.setId(5L);
        employee.setName("John Smith");
        employee.setAge(33);
        employee.setPosition("PositionA");
        employee.setOrganization(new Organization(1L, "Organization1", "Org Street No. 1"));

        employee = employeeRepository.save(employee);

        assertNotNull(employee);
        assertNotNull(employee.getId());
    }

    @Test
    void findById() {
        Optional<Employee> optionalEmployee = employeeRepository.findById(1L);

        assertTrue(optionalEmployee.isPresent());
        assertEquals("John Doe", optionalEmployee.get().getName());
    }

    @Test
    void findByOrganizationName() {
        List<Employee> employees = employeeRepository.findByOrganizationName("Organization2");

        assertFalse(employees.isEmpty());
        assertEquals(2, employees.size());
        assertEquals("Ann Smith", employees.get(1).getName());
    }

    @Test
    void findByName() {
        List<Employee> employees = employeeRepository.findByName("Doe");

        assertFalse(employees.isEmpty());
        assertEquals(2, employees.size());
        assertEquals(1L, employees.get(0).getId());
    }

    @Test
    void deleteById() {
        employeeRepository.deleteById(5L);
        Optional<Employee> optionalEmployee = employeeRepository.findById(5L);

        assertFalse(optionalEmployee.isPresent());
    }
}
