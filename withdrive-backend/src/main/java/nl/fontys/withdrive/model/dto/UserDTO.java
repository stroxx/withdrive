package nl.fontys.withdrive.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class UserDTO {
    @Id
    @Column(name="ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    private @Getter @Setter UUID clientNumber = UUID.randomUUID();
    private @Getter @Setter String email;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String dateOfBirth;
    private @Getter @Setter String gender;
    private @Getter @Setter String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter String password;

}







