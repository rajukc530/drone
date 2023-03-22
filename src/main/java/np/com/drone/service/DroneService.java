package np.com.drone.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import np.com.drone.dto.DroneDto;
import np.com.drone.dto.DoneLoadDto;
import np.com.drone.modal.Drone;

@Component
public interface DroneService {

    Drone register(DroneDto drone);

    List<Drone> getAvailabeDrones();

    BigDecimal getBateryLevelBySerialNumber(String serialNumber) throws Exception;

    boolean checkLoadedMedicationForADrone(String serialno);

    void loadDrone(DoneLoadDto loadRequest);

}
