package niesuv.project.film.repository;

import niesuv.project.film.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageFilmRepository extends JpaRepository<Film, Long> {
    public Page<Film> findAll(Pageable page);
}