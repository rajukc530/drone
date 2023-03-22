package np.com.drone.modal;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import np.com.drone.enums.Model;
import np.com.drone.enums.State;

@Entity
@Table(name = "drone")
@Getter
@Setter
@NoArgsConstructor
public class Drone {
    @Id
    @Column(name = "serial_no", length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit")
    @DecimalMax(value = "500", message = "Drone cannot carry more than {value} gms")
    private double weightLimit;

    @Column(name = "battery_capacity", precision = 3, scale = 2)
    private BigDecimal batteryCapacity;

    @Column(name = "drone_state")
    @Enumerated(EnumType.STRING)
    private State state;

}
