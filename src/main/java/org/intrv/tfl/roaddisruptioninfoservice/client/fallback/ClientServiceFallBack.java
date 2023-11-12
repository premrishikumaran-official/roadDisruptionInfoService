package org.intrv.tfl.roaddisruptioninfoservice.client.fallback;

import org.intrv.tfl.roaddisruptioninfoservice.client.api.ClientService;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadSeverityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientServiceFallBack implements ClientService {
    @Override
    public ResponseEntity<RoadSeverityStatus[]> getSeverityByRoad(String roadId) {
        RoadSeverityStatus[] roadSeverityStatuses = new RoadSeverityStatus[]{};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(roadSeverityStatuses);
    }
}
