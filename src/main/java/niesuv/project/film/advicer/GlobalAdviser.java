package niesuv.project.film.advicer;

import niesuv.project.film.customException.BadRequestException;
import niesuv.project.film.customException.CredentialsException;
import niesuv.project.film.customException.UnvalidJWT;
import niesuv.project.film.customException.UserNameExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviser {

    @ExceptionHandler(CredentialsException.class)
    public ResponseEntity<String> handle0(CredentialsException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(UserNameExistException.class)
    public ResponseEntity<String> handle1() {
        return ResponseEntity.status(HttpStatusCode.valueOf(409))
                .body("Username has been used by another!");
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handle2(BadRequestException exception) {
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(UnvalidJWT.class)
    public ResponseEntity<String> handle3(UnvalidJWT exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
    }
}
