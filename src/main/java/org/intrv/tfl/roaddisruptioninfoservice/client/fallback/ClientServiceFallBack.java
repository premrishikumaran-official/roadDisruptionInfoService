package org.intrv.tfl.roaddisruptioninfoservice.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.intrv.tfl.roaddisruptioninfoservice.client.api.ClientService;
import org.intrv.tfl.roaddisruptioninfoservice.client.model.RoadSeverityStatusResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientServiceFallBack implements ClientService {
    @Override
    public RoadSeverityStatusResponse[] getSeverityByRoad(String roadId) {
        log.error("Cannot get meaningful response from Client ,Falling back to default value");
        return new RoadSeverityStatusResponse[]{};
    }
}
