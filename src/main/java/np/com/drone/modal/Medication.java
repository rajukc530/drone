package np.com.drone.modal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medication")
@Getter
@Setter
@NoArgsConstructor
public class Medication {

    @Id
    @Pattern(regexp = "[A-Z0-9_]+", message = "allowed only upper case letters, underscore and numbers")
    private String code;

    @Pattern(regexp = "[a-zA-Z_0-9-]+", message = "allowed only letters, numbers, - and _")
    private String name;

    private double weight;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_drone")
    private Drone drone;
}
