package org.intrv.tfl.roaddisruptioninfoservice.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadSeverityStatusResponse {
    public String id;
    public String displayName;
    public String statusSeverity;
    public String statusSeverityDescription;
    public String bounds;
    public String envelope;
    public String url;
}
