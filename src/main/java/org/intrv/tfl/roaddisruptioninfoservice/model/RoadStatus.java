package org.intrv.tfl.roaddisruptioninfoservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoadStatus {

    private String displayName;
    private String statusSeverity;
    private String statusSeverityDescription;

}
