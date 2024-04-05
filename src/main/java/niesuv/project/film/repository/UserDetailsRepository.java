package niesuv.project.film.repository;

import niesuv.project.film.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    boolean existsByUsername(@NonNull String username);

    UserDetails findByUsername(@NonNull String username);
}