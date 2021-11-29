package nl.fontys.withdrive.service;

import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.interfaces.converter.IApplicationConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService implements IApplicationService {
    private final IApplicationData saved;
    private final IApplicationConverter applicationConverter;

    @Autowired
    public ApplicationService(IApplicationData saved, IApplicationConverter applicationConverter){
        this.saved = saved;
        this.applicationConverter = applicationConverter;
    }

    @Override
    public void Add(ApplicationRequestDTO application, UUID user) {
        if(application.getStatus().equals(ApplicationStatus.PENDING)){
            application.setUser(user);
            TripApplication app = applicationConverter.RequestDTOToEntity(application);
            if(app.getTrip().getDriver().getUserID() != user){
                saved.Create(app);
            }
        }
    }

    @Override
    public List<ApplicationResponseDTO> RetrieveByTripID(UUID id) {
        return applicationConverter.ListEntityToResponseDTO(saved.RetrieveByTripID(id));
    }

    @Override
    public List<ApplicationResponseDTO> RetrieveByUserID(UUID id) {
        return applicationConverter.ListEntityToResponseDTO(saved.RetrieveByUserID(id));
    }

    @Override
    public ApplicationRequestDTO RetrieveByUserIDAndTripID(UUID uID, UUID tID) {
        return applicationConverter.EntityToRequestDTO(saved.RetrieveByUserIDAndTripID(uID,tID));
    }

    @Override
    public void RespondToApplication(ApplicationRequestDTO application, ApplicationStatus status) {
        TripApplication temp = applicationConverter.RequestDTOToEntity(RetrieveByUserIDAndTripID(application.getUser(),application.getTrip()));
        temp.setStatus(status);
        saved.Update(temp);
    }

    @Override
    public void Update(ApplicationRequestDTO application) {
        TripApplication temp = applicationConverter.RequestDTOToEntity(RetrieveByUserIDAndTripID(application.getUser(),application.getTrip()));
        if(temp.getStatus().equals(ApplicationStatus.PENDING)){
            saved.Update(applicationConverter.RequestDTOToEntity(application));
        }
    }
}
