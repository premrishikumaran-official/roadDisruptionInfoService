package org.intrv.tfl.roaddisruptioninfoservice.controller;

import org.intrv.tfl.roaddisruptioninfoservice.model.RoadStatus;
import org.intrv.tfl.roaddisruptioninfoservice.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/road")
public class RoadStatusController {

    @Autowired
    private RoadService roadService;

   @GetMapping(value = "/{id}/severity-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoadStatus> getStatus(@PathVariable String id){
       RoadStatus severityStatus = roadService.getSeverityStatus(id);
        return ResponseEntity.ok(severityStatus);
    }

}
