package com.poc.spring.infinispan.data.dao;

import com.poc.spring.infinispan.data.entity.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRepository {

    @Cacheable(cacheNames = "cacheEmployeeByName")
    public Employee findByName(String name)
            throws InterruptedException {

        Thread.sleep(5000);
        return new Employee(name);
    }

    @Cacheable(cacheNames = "cacheEmployeeByDepartmentAndName")
    public Employee findByDepartmentAndName(String dptCode, String name)
            throws InterruptedException {

        Thread.sleep(5000);
        return new Employee(name);
    }
}