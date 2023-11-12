package org.intrv.tfl.roaddisruptioninfoservice.service;

import org.intrv.tfl.roaddisruptioninfoservice.model.RoadStatus;

public interface RoadService {
     RoadStatus getSeverityStatus(String roadId);
}
