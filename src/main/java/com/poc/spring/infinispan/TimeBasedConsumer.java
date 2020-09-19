package com.poc.spring.infinispan;

import com.poc.spring.infinispan.data.dao.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class TimeBasedConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(TimeBasedConsumer.class);
    private static final List<String> CODES_EMPLOYEE_NAME = Arrays.asList("John", "Jane", "Smith", "David", "Cole");

    private final EmployeeRepository employeeRepository;
    private final Random random;

    TimeBasedConsumer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.random = new Random();
    }

    @Scheduled(fixedDelay = 2000)
    public void retrieveEmployee()
            throws InterruptedException {

        final String randomName = CODES_EMPLOYEE_NAME.get(this.random.nextInt(CODES_EMPLOYEE_NAME.size()));
        long timeStart = System.currentTimeMillis();

//        this.employeeRepository.findByName(randomName);
        this.employeeRepository.findByDepartmentAndName("DPT_ID", randomName);

        long timeEnd = System.currentTimeMillis();
        LOG.info(String.format("Looked up the Employee %s in %d ms", randomName, (timeEnd - timeStart)));
    }
}