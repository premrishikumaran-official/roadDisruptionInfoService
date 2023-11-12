package org.intrv.tfl.roaddisruptioninfoservice.client.api;

import org.intrv.tfl.roaddisruptioninfoservice.client.config.FeignClientConfiguration;
import org.intrv.tfl.roaddisruptioninfoservice.client.fallback.ClientServiceFallBack;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadSeverityStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "road-disruption-info-service-provider",
        url = "${infoServiceProvider.url}",configuration = FeignClientConfiguration.class,
        fallback = ClientServiceFallBack.class)
public interface ClientService {

    @GetMapping(path = "/Road/{roadId}?app_id=${infoServiceProvider.appId}&app_key=${infoServiceProvider.appKey}")
    ResponseEntity<RoadSeverityStatus[]> getSeverityByRoad(@PathVariable String roadId);

}
