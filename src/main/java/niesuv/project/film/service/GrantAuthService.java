package niesuv.project.film.service;

import lombok.NoArgsConstructor;
import niesuv.project.film.EmbeddedType.ROLE;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class GrantAuthService {

    public List<GrantedAuthority> convertRoleToAuth(ROLE role) {
        return new ArrayList<>(List.of(new SimpleGrantedAuthority(role.name())));
    }


}
