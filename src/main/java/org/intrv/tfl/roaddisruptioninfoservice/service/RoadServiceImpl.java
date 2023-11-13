package org.intrv.tfl.roaddisruptioninfoservice.service;

import org.intrv.tfl.roaddisruptioninfoservice.client.api.ClientService;
import org.intrv.tfl.roaddisruptioninfoservice.client.exception.NotFoundException;
import org.intrv.tfl.roaddisruptioninfoservice.exception.NonProcessableContentError;
import org.intrv.tfl.roaddisruptioninfoservice.client.model.RoadSeverityStatusResponse;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadStatus;
import org.springframework.stereotype.Service;

@Service
public class RoadServiceImpl implements RoadService {

    private final ClientService clientService;

    public RoadServiceImpl(ClientService tflService) {
        this.clientService = tflService;
    }

    @Override
    public RoadStatus getSeverityStatus(String roadId) {

        RoadSeverityStatusResponse[] severityStatuses = clientService.getSeverityByRoad(roadId);

        if (severityStatuses == null || severityStatuses.length == 0) {
            throw new NotFoundException("Expecting a response but received no value");
        }

        if (severityStatuses.length > 1) {
            throw new NonProcessableContentError("Expecting one record but received : "+ severityStatuses.length);
        }

        RoadSeverityStatusResponse severityStatus = severityStatuses[0];

        return RoadStatus.builder()
                .displayName(severityStatus.getDisplayName())
                .statusSeverity(severityStatus.getStatusSeverity())
                .statusSeverityDescription(severityStatus.getStatusSeverityDescription())
                .build();
    }
}
