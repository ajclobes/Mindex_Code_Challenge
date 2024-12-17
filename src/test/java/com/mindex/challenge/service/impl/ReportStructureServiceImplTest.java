package com.mindex.challenge.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportStructureServiceImplTest {
    
    private String reportStructureUrl;
    private String reportStructureIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Before
    public void setup() {
        reportStructureIdUrl = "http://localhost:" + port + "/reportStructure/{id}";
    }

    @Test
    public void testRead() {
        String id = "b7839309-3348-463b-a7e3-5de1c168beb3";
        Employee employee = new Employee();
        employee.setEmployeeId(id);
        ReportingStructure testReportStructure = reportingStructureService.getReportStructure(id);

        // null read check
        assertEquals(testReportStructure.getEmployee().getEmployeeId(), employee.getEmployeeId());
        assertEquals(testReportStructure.getNumberOfReports(), 0);

        // tree check
        id = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        employee.setEmployeeId(id);
        testReportStructure = reportingStructureService.getReportStructure(id);

        assertEquals(testReportStructure.getEmployee().getEmployeeId(), employee.getEmployeeId());
        assertEquals(testReportStructure.getNumberOfReports(), 4);
    }
}