package np.com.drone.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import np.com.drone.dto.DroneDto;
import np.com.drone.dto.DoneLoadDto;
import np.com.drone.enums.State;
import np.com.drone.modal.Drone;
import np.com.drone.modal.Medication;
import np.com.drone.repository.DroneRepository;
import np.com.drone.repository.MedicationRepository;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Drone register(DroneDto req) {
        Drone entity = new Drone();
        entity.setSerialNumber(req.getSerialNumber());
        entity.setModel(req.getModel());
        entity.setWeightLimit(0.0);
        entity.setBatteryCapacity(req.getBattery());
        entity.setState(State.IDLE);
        return droneRepository.save(entity);
    }

    @Override
    public List<Drone> getAvailabeDrones() {
        String state = "IDLE";
        return droneRepository.findAllByState(state);
    }

    @Override
    public BigDecimal getBateryLevelBySerialNumber(String serialNumber) {

        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new RuntimeException("Drone battery level details not found");
        }

        return drone.getBatteryCapacity();
    }

    @Override
    public boolean checkLoadedMedicationForADrone(String serialno) {

        Drone drone = droneRepository.findBySerialNumber(serialno);

        // validate before loading
        if (drone == null) {
            throw new RuntimeException("Drone specified does not exist");
        }

        // check battery
        if (drone.getBatteryCapacity().compareTo(new BigDecimal(0.25)) < 0) {
            throw new RuntimeException("The Drone cannot be loaded, battery below 25%");
        }

        return true;
    }

    @Override
    public void loadDrone(DoneLoadDto loadRequest) {
        Drone drone = droneRepository.findBySerialNumber(loadRequest.getSerialNumber());
        Medication medication = medicationRepository.findByCode(loadRequest.getCode());

        // validate before loading
        if (drone == null) {
            throw new RuntimeException("Drone specified does not exist");
        }

        if (medication == null) {
            throw new RuntimeException("Medication specified does not exist");
        }

        double newWeight = drone.getWeightLimit() + medication.getWeight();

        if (newWeight > 500) {
            throw new RuntimeException("The Drone cannot load more than the weight limit");
        }
        // check battery
        if (drone.getBatteryCapacity().compareTo(new BigDecimal(0.25)) < 0) {
            throw new RuntimeException("The Drone cannot be loaded, battery below 25%");
        }

        if (newWeight <= 500) {
            drone.setWeightLimit(newWeight);
            drone.setState(State.LOADED);
            droneRepository.save(drone);
        }

    }

}
