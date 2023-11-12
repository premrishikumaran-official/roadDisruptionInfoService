package org.intrv.tfl.roaddisruptioninfoservice.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.intrv.tfl.roaddisruptioninfoservice.client.api.ClientService;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadSeverityStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientServiceFallBack implements ClientService {
    @Override
    public RoadSeverityStatus[] getSeverityByRoad(String roadId) {
        log.error("Cannot get meaning response from Client ,Falling back to default value");
        return new RoadSeverityStatus[]{};
    }
}
