package com.mindex.challenge.data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Compensation type class
 * Added employeeId to fields. From a DB perspective, compensation would only need to be
 * linked to an employee via id (we could make a view if we want to see them together).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Compensation {
    
    private String employeeId;
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime effectiveDate;

    public Compensation() {
    }

    public void setEmployeeId(String id) {
        this.employeeId = id;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setEffectiveDate(LocalDateTime date) {
        this.effectiveDate = date;
    }

    public LocalDateTime getEffectiveDate() {
        return this.effectiveDate;
    }
}
