package niesuv.project.film.entity;

import jakarta.persistence.*;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "commentid", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filmid")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserDetails user;

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
