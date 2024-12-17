package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

/*
 * ReportingStructureController class
 * Made a seperate controller/service and implimentation for this and Compensation with
 * scalability and single-responsibility in mind. This type may evolve (we may want to add
 * recursive functionality mapping, for example.
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    /*
     * API call to return a filled out ReportStructure type
     * @param id EmployeeId of employee at top of report tree.
     * @return ReportStructure object
     */
    @GetMapping("/reportStructure/{id}")
    public ReportingStructure getReportStructure(@PathVariable String id) {
        LOG.debug("Received report structure request for id [{}]", id);

        return reportingStructureService.getReportStructure(id);
    }
}
