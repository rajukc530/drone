package np.com.drone.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import np.com.drone.enums.Model;
import np.com.drone.enums.State;

@Getter
@Setter
public class DroneDto {

    @NotNull
    private String serialNumber;

    @NotNull
    private Model model;

    private double weightLimit;

    @NotNull
    private BigDecimal battery;

    private State state;

}
