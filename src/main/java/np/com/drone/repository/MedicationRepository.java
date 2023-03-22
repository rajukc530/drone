package np.com.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import np.com.drone.modal.Medication;

public interface MedicationRepository extends JpaRepository<Medication, String> {

    Medication findByCode(@Param("code") String code);

}
