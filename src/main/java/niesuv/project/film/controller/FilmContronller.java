package niesuv.project.film.controller;

import lombok.AllArgsConstructor;
import lombok.Setter;
import niesuv.project.film.entity.Film;
import niesuv.project.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/films")
@AllArgsConstructor
@Setter
public class FilmContronller {

    @Autowired
    private FilmService filmService;

    @RequestMapping("")
    public ResponseEntity<Page<Film>> getALlFilm(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "filmname") String sortBy) {
        return ResponseEntity.ok(filmService.getAllFilm(page, size, sortBy));
    }

}
