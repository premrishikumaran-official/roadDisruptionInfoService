package org.intrv.tfl.roaddisruptioninfoservice.service;

import org.intrv.tfl.roaddisruptioninfoservice.client.config.ClientService;
import org.intrv.tfl.roaddisruptioninfoservice.exception.UnKnowError;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadSeverityStatus;
import org.intrv.tfl.roaddisruptioninfoservice.model.RoadStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoadServiceImplTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    RoadServiceImpl roadService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("valid road id should have correct display name")
    public void whenAValidRoadIdProvided_returnRoadDisplayedName() {
        //given
        String roadId = "AA";
        when(clientService.getSeverityByRoad(roadId)).
                thenReturn(createRoadSeverityResponse(roadId,null,null));

        //when
        RoadStatus roadStatus = roadService.getSeverityStatus(roadId);


        //then
        verify(clientService,times(1)).getSeverityByRoad(roadId);
        assertEquals(roadId,roadStatus.getDisplayName());
    }

    @Test
    @DisplayName("valid road id should have correct Status Severity")
    public void whenAValidRoadIdProvided_returnRoadStatusSeverity() {
        //given
        String roadId = "AA";
        String severityStatus = "Good";
        when(clientService.getSeverityByRoad(roadId)).
                thenReturn(createRoadSeverityResponse(roadId,severityStatus,null));

        //when
        RoadStatus roadStatus = roadService.getSeverityStatus(roadId);


        //then
        verify(clientService,times(1)).getSeverityByRoad(roadId);
        assertEquals(roadId,roadStatus.getDisplayName());
        assertEquals(severityStatus,roadStatus.getStatusSeverity());

    }

    @Test
    @DisplayName("valid road id should have correct Status Severity Desc")
    public void whenAValidRoadIdProvided_returnRoadStatusSeverityDesc() {
        //given
        String roadId = "AA";
        String severityStatus = "Good";
        String desc = "No Exceptional Delays";
        when(clientService.getSeverityByRoad(roadId)).
                thenReturn(createRoadSeverityResponse(roadId,severityStatus,desc));

        //when
        RoadStatus roadStatus = roadService.getSeverityStatus(roadId);


        //then
        verify(clientService,times(1)).getSeverityByRoad(roadId);
        assertEquals(roadId,roadStatus.getDisplayName());
        assertEquals(severityStatus,roadStatus.getStatusSeverity());
        assertEquals(desc,roadStatus.getStatusSeverityDescription());
    }


    @Test
    @DisplayName("When client service return no value ")
    public void whenApiReturnNoValue_throwsError() {
        //given
        String roadId = "AA";

        when(clientService.getSeverityByRoad(roadId)).
                thenReturn(ResponseEntity.ok().build());

        //then
        assertThrows(UnKnowError.class, () -> {
            //when
            roadService.getSeverityStatus(roadId);
        });

    }

    @Test
    @DisplayName("When client service return un expected result ")
    public void whenApiReturnAmbiguousList_throwsError() {
        //given
        String roadId = "AA";
        RoadSeverityStatus[] roadSeverityStatuses =new RoadSeverityStatus[]{
                RoadSeverityStatus.builder().build(),
                RoadSeverityStatus.builder().build()};

        when(clientService.getSeverityByRoad(roadId)).
                thenReturn(ResponseEntity.ok(roadSeverityStatuses));

        //then
        assertThrows(UnKnowError.class, () -> {
            //when
            roadService.getSeverityStatus(roadId);
        });

    }

    private ResponseEntity<RoadSeverityStatus[]> createRoadSeverityResponse(String roadId,
                                                                            String severityStatus,
                                                                            String desc) {
        RoadSeverityStatus roadSeverityStatus = RoadSeverityStatus.builder()
                .statusSeverity(severityStatus)
                .statusSeverityDescription(desc)
                .displayName(roadId).build();

        return ResponseEntity.ok(new RoadSeverityStatus[]{roadSeverityStatus});
    }


}