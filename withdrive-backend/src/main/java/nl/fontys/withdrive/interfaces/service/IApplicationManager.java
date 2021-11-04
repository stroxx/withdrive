package nl.fontys.withdrive.interfaces.service;

import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.enumeration.ApplicationStatus;

import java.util.List;
import java.util.UUID;

public interface IApplicationManager {
    void Add(ApplicationRequestDTO application);

    List<ApplicationResponseDTO> RetrieveByTripID(UUID id);

    List<ApplicationResponseDTO> RetrieveByUserID(UUID id);

    ApplicationRequestDTO RetrieveByUserIDAndTripID(UUID uID, UUID tID);

    void RespondToApplication(ApplicationRequestDTO application, ApplicationStatus status);

    void Update(ApplicationRequestDTO application);
}
