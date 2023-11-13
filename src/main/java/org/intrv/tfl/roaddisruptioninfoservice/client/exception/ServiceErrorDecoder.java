package org.intrv.tfl.roaddisruptioninfoservice.client.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class ServiceErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        final String messageStr = message == null ? "" : message.getMessage();
        switch (response.status()) {
            case 400:
            case 401:
                return new RuntimeException(messageStr.isEmpty()
                        ? "Bad Request"
                        : messageStr
                );
            case 404:
                return new NotFoundException(messageStr.isEmpty()
                        ? "Not found"
                        : messageStr
                );
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }
}

