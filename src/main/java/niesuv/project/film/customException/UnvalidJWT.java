package niesuv.project.film.customException;

public class UnvalidJWT extends RuntimeException{
    public UnvalidJWT(String msg) {
        super(msg);
    }
}
