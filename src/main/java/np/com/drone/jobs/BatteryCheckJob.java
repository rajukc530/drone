package np.com.drone.jobs;

import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import np.com.drone.modal.Drone;
import np.com.drone.repository.DroneRepository;

@Component
@EnableAsync
public class BatteryCheckJob {

    @Autowired
    private DroneRepository droneRepository;

    static final Logger log = LoggerFactory.getLogger(BatteryCheckJob.class);

    @Scheduled(fixedRate = 60 * 1000) // in every mins.
    public void scheduleFixedRateTaskAsync() throws InterruptedException {

        List<Drone> drones = droneRepository.findAll();

        DecimalFormat decimalFormat = new DecimalFormat("#%");
        //save in logfile or db
        for (Drone drone : drones) {
            log.info("Batery level--: SerialNumber  " + drone.getSerialNumber() + "  Battery Level " + decimalFormat.format(drone.getBatteryCapacity()));
        }

    }

}
