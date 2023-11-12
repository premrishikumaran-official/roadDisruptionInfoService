package org.intrv.tfl.roaddisruptioninfoservice.service;

import org.intrv.tfl.roaddisruptioninfoservice.client.config.ClientService;
import org.intrv.tfl.roaddisruptioninfoservice.exception.UnKnowError;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadSeverityStatus;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoadServiceImpl implements RoadService {

    private final ClientService clientService;

    public RoadServiceImpl(ClientService tflService) {
        this.clientService = tflService;
    }

    @Override
    public RoadStatus getSeverityStatus(String roadId) {

        ResponseEntity<RoadSeverityStatus[]> response = clientService.getSeverityByRoad(roadId);

        RoadSeverityStatus[] severityStatuses = response.getBody();

        if (severityStatuses == null) {
            throw new UnKnowError("Expecting one record but received no value");
        }

        if (severityStatuses.length > 1) {
            throw new UnKnowError("Expecting one record but received : "+ severityStatuses.length);
        }

        RoadSeverityStatus severityStatus = severityStatuses[0];

        return RoadStatus.builder()
                .displayName(severityStatus.getDisplayName())
                .statusSeverity(severityStatus.getStatusSeverity())
                .statusSeverityDescription(severityStatus.getStatusSeverityDescription())
                .build();
    }
}
