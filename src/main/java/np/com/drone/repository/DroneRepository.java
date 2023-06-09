package np.com.drone.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import np.com.drone.modal.Drone;

@Transactional
public interface DroneRepository extends JpaRepository<Drone, String> {

    List<Drone> findAllByState(@Param("state") String state);

    @Query(value = "SELECT d from Drone d where d.serialNumber =:id ")
    Drone findBySerialNumber(@Param("id") String id);

    @Modifying
    @Query(value = "update Drone d set d.state = :state where  d.serialNumber= :serialno")
    void setUpdateState(@Param("state") String status, @Param("serialno") String serialno);

}
