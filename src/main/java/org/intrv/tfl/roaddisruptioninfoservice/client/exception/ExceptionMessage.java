package org.intrv.tfl.roaddisruptioninfoservice.client.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionMessage {
           private String exceptionType;
           private String httpStatusCode;
           private String httpStatus;
           private String relativeUri;
           private String message;
}