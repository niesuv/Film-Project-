package niesuv.project.film.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import niesuv.project.film.customException.IllegalPageRequestException;
import niesuv.project.film.entity.Film;
import niesuv.project.film.repository.PageFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Setter
@AllArgsConstructor
public class FilmService {
    @Autowired
    private PageFilmRepository filmRepository;


    public Page<Film> getAllFilm(int page, int size, String sort) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
            return filmRepository.findAll(pageable);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalPageRequestException(e.getMessage());
        }

    }

}
