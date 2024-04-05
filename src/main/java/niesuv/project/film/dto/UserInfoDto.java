package niesuv.project.film.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import niesuv.project.film.EmbeddedType.ROLE;

import java.io.Serializable;

/**
 * DTO for {@link niesuv.project.film.entity.UserDetails}
 */
@Value
@AllArgsConstructor
@Getter
@Setter
public class UserInfoDto implements Serializable {
    Long id;
    String username;
    ROLE role;
}