package niesuv.project.film.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link niesuv.project.film.entity.UserDetails}
 */
@Value
@AllArgsConstructor
@Getter
@Setter
public class UserSignupDTO implements Serializable {

    @NotNull(message = "Username cannot be blank")
    String username;
    @Size(min = 6, message = "Password must at least 6 chars")
    String password;
}