package niesuv.project.film.customException;

public class UserNameExistException extends RuntimeException{

    public UserNameExistException(String mes) {
        super(mes);
    }
}
