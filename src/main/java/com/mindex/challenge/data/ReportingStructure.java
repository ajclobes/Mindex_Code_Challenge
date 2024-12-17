package com.mindex.challenge.data;

public class ReportingStructure {
 
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(Employee employee, Integer numReports) {
        this.employee = employee;
        this.numberOfReports = numReports;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Integer getNumberOfReports() {
        return this.numberOfReports;
    }
}
