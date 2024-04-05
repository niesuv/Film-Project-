package niesuv.project.film.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filmid", nullable = false)
    private Long filmid;

    @Column(name = "filmname", nullable = false)
    private String filmname;

    @OneToMany(mappedBy = "film", orphanRemoval = true)
    @JsonIgnore
    private List<Comments> comments = new ArrayList<>();

    @Column(name = "filmlength", nullable = false)
    private int filmlength;

    @Column(name = "filmurl", nullable = false)
    private String mfilmurl;

}