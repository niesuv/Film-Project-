package niesuv.project.film.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import niesuv.project.film.customException.BadRequestException;
import niesuv.project.film.dto.UserInfoDto;
import niesuv.project.film.dto.UserSigninDTO;
import niesuv.project.film.dto.UserSignupDTO;
import niesuv.project.film.entity.UserDetails;
import niesuv.project.film.model.JWTResponse;
import niesuv.project.film.service.JWTService;
import niesuv.project.film.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Getter
@Setter
public class UserRestControllers {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("")
    String hello() {
        return "Hello world!";
    }

    @PostMapping("/signup")
    ResponseEntity<UserInfoDto> signup(@RequestBody @Valid UserSignupDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        UserDetails userDetails = userService.newUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.convertValue(userDetails, UserInfoDto.class));
    }

    @PostMapping("/signin")
    ResponseEntity<JWTResponse> singin(@RequestBody @Valid UserSigninDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        UserDetails userDetails = userService.signin(dto);
        JWTResponse data = new JWTResponse(jwtService.generateToken(userDetails));
        ResponseEntity<JWTResponse> _response = ResponseEntity.status(HttpStatus.CREATED).body(data);
        return _response;
    }
}
