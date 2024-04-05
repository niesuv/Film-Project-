package niesuv.project.film.customException;

public class IllegalPageRequestException extends RuntimeException{
    public IllegalPageRequestException(String msg) {
        super(msg);
    }
}
