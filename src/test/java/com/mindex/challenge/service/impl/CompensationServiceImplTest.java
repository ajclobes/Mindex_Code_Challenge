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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    
    private String compensationUrl;
    private String compensationIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployeeId("62c1084e-6e34-4630-93fd-9153afb22222");
        BigDecimal salTest = new BigDecimal(100.00); // poor guy
        testCompensation.setSalary(salTest);
        LocalDateTime dateTest = LocalDate.of(2025, Month.JANUARY, 01).atStartOfDay();
        testCompensation.setEffectiveDate(dateTest);

        // create check
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation.getEmployeeId());
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // read check
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation.getEmployeeId()).getBody();
        assertEquals(createdCompensation.getEmployeeId(), readCompensation.getEmployeeId());
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    private static void assertCompensationEquivalence(Compensation testCompensation, Compensation createdCompensation) {
        assertEquals(testCompensation.getEmployeeId(), createdCompensation.getEmployeeId());
        assertEquals(testCompensation.getSalary(), createdCompensation.getSalary());
        assertEquals(testCompensation.getEffectiveDate(), createdCompensation.getEffectiveDate());
    }
}
