package org.intrv.tfl.roaddisruptioninfoservice.controller;

import com.github.tomakehurst.wiremock.http.Fault;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.intrv.tfl.roaddisruptioninfoservice.exception.ErrorResponse;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
class RoadStatusControllerIT {

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        createStub();

    }

    @Test
    @DisplayName("When a valid road  is provided, get 200 ok response")
    public void whenAValidRoadIdProvided_return200Ok() {
        RoadStatus expectedRoadStatus = RoadStatus.builder().
                statusSeverityDescription("No Exceptional Delays")
                .statusSeverity("Good")
                .displayName("A2").build();


        RoadStatus response = given()
                .when()
                .get("/v1/road/A2/severity-status")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(RoadStatus.class);

        assertEquals(expectedRoadStatus.getStatusSeverity(), response.getStatusSeverity());
        assertEquals(expectedRoadStatus.getStatusSeverityDescription(), response.getStatusSeverityDescription());
        assertEquals(expectedRoadStatus.getDisplayName(), response.getDisplayName());
    }

    @Test
    @DisplayName("When a non valid road  is provided , get 404 Not found response")
    public void whenNonValidRoadIdProvided_return404NotFoundReturned() {

        ErrorResponse response = given()
                .when()
                .get("/v1/road/XY/severity-status")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .body().as(ErrorResponse.class);
    }


    @Test
    @DisplayName("When another valid road is provided we get 200 ok response")
    public void whenAnotherValidRoadIdProvided_return200Ok() {
        RoadStatus expectedRoadStatus = RoadStatus.builder().
                statusSeverityDescription("No Exceptional Delays")
                .statusSeverity("Good")
                .displayName("South Circular (A205)").build();


        RoadStatus response = given()
                .when()
                .get("/v1/road/A205/severity-status")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(RoadStatus.class);

        assertEquals(expectedRoadStatus.getStatusSeverity(), response.getStatusSeverity());
        assertEquals(expectedRoadStatus.getStatusSeverityDescription(), response.getStatusSeverityDescription());
        assertEquals(expectedRoadStatus.getDisplayName(), response.getDisplayName());
    }

    @Test
    @DisplayName("When valid road with serious disruption is provided we get 200 ok response")
    public void whenValidRoadWithSeriousDisruptionProvided_return200Ok() {
        RoadStatus expectedRoadStatus = RoadStatus.builder().
                statusSeverityDescription("Serious")
                .statusSeverity("Serious")
                .displayName("A4").build();


        RoadStatus response = given()
                .when()
                .get("/v1/road/A4/severity-status")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(RoadStatus.class);

        assertEquals(expectedRoadStatus.getStatusSeverity(), response.getStatusSeverity());
        assertEquals(expectedRoadStatus.getStatusSeverityDescription(), response.getStatusSeverityDescription());
        assertEquals(expectedRoadStatus.getDisplayName(), response.getDisplayName());
    }

    @Test
    @DisplayName("When a connection is reset by peer receive 500 error")
    public void whenConnectionResetError_return500Ok() {

        ErrorResponse response = given()
                .when()
                .get("/v1/road/CXY/severity-status")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .extract()
                .body().as(ErrorResponse.class);

        assertNotNull(response);

    }

    @Test
    @DisplayName("When a internal error thrown receive 500 error")
    public void whenInternalError_return500Ok() {

        ErrorResponse response = given()
                .when()
                .get("/v1/road/INTErr/severity-status")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .extract()
                .body().as(ErrorResponse.class);

        assertNotNull(response);

    }


    //Stub creation

    private void createStub() {
        stubFor(
                get(urlEqualTo("/Road/A2?app_id=road-app&app_key=xyz"))
                        .willReturn(aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("content-type", "application/json; charset=utf-8")
                                .withBodyFile("response/a2RoadWithStatusDisplayNameAndSeverity.json")));

        stubFor(
                get(urlEqualTo("/Road/A205?app_id=road-app&app_key=xyz"))
                        .willReturn(aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("content-type", "application/json; charset=utf-8")
                                .withBodyFile("response/a205RoadWithStatusDisplayNameAndSeverity.json")));

        stubFor(
                get(urlEqualTo("/Road/A4?app_id=road-app&app_key=xyz"))
                        .willReturn(aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("content-type", "application/json; charset=utf-8")
                                .withBodyFile("response/a4RoadWithStatusSeriousDisruption.json")));


        stubFor(
                get(urlEqualTo("/Road/XY?app_id=road-app&app_key=xyz"))
                        .willReturn(aResponse()
                                .withStatus(HttpStatus.NOT_FOUND.value())
                                .withHeader("content-type", "application/json; charset=utf-8")
                                .withBodyFile("response/notFoundResponse.json")));


        stubFor(get(urlEqualTo("/Road/INTErr?app_id=road-app&app_key=xyz"))
                .willReturn(aResponse()
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));


        stubFor(get(urlEqualTo("/Road/internalError?app_id=road-app&app_key=xyz"))
                .willReturn(aResponse()
                        .withStatus(500)));

    }


}