package com.poc.spring.infinispan.data.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable {

    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee employee = (Employee) o;

        return this.name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}