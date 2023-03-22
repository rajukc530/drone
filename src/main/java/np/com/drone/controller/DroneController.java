package np.com.drone.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import np.com.drone.dto.DroneDto;
import np.com.drone.dto.DoneLoadDto;
import np.com.drone.modal.Drone;
import np.com.drone.service.DroneService;

@RestController
@RequestMapping(path = "/api/drone")
public class DroneController {
    @Autowired
    private DroneService droneService;

    // 1. register a drone
    @PostMapping("/register")
    public ResponseEntity<Drone> register(@Valid @RequestBody DroneDto req) {
        Drone object = droneService.register(req);
        return new ResponseEntity<Drone>(object, HttpStatus.CREATED);
    }

    // 2. loading a drone with medication items.
    @PostMapping("/load")
    public ResponseEntity<String> loadDroneWithMedication(@Valid @RequestBody DoneLoadDto loadrequest) {
        droneService.loadDrone(loadrequest);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // 3. checking loaded medication items for a given drone;
    @GetMapping("/check-load/{serialNumber}")
    public ResponseEntity<Boolean> checkLoadedMedicationForADrone(@PathVariable("serialNumber") String serialNumber) {
        boolean isLoaded = droneService.checkLoadedMedicationForADrone(serialNumber);
        return new ResponseEntity<Boolean>(isLoaded, HttpStatus.OK);
    }

    // 4. checking available drones for loading
    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailabeDrones() {
        List<Drone> drones = droneService.getAvailabeDrones();
        return new ResponseEntity<List<Drone>>(drones, HttpStatus.OK);
    }

    // 5. check drone battery level for a given drone
    @GetMapping("/check-battery/{serialNumber}")
    public ResponseEntity<BigDecimal> checkBateryLevelBySerialNumber(@PathVariable("serialNumber") String serialNumber) throws Exception {
        BigDecimal batteryPercentage = droneService.getBateryLevelBySerialNumber(serialNumber);
        return new ResponseEntity<BigDecimal>(batteryPercentage, HttpStatus.OK);
    }

}
