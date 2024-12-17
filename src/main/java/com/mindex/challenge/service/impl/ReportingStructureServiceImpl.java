package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
    
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /*
     * Constructs a ReportStructure type using employee and number of reports
     */
    @Override
    public ReportingStructure getReportStructure(String id){
        LOG.debug("Retreiving report structure for id [{}]", id);

        // collect/calculate values
        Employee employee = employeeRepository.findByEmployeeId(id);
        Integer numberOfReports = calculateNumberOfReports(employee);

        // construct ReportStructure
        ReportingStructure reportStructure = new ReportingStructure(employee, numberOfReports);

        return reportStructure;
    }

    /*
    * Recursively run through direct reports under an employee to total them
    * @param employee the given employee to be calculated
    */
    private Integer calculateNumberOfReports(Employee employee) {

        // tracker
        Integer total = 0;

        // base case/bulletproofing
        if (employee.getDirectReports() != null && !employee.getDirectReports().isEmpty()) {

            // recursively go down the reports and collect them
            for (Employee directReports : employee.getDirectReports()) {
                Employee report = employeeRepository.findByEmployeeId(directReports.getEmployeeId());
                total++;
                total += calculateNumberOfReports(report);
            }
        }

        // set field
        return total;
    }
}
