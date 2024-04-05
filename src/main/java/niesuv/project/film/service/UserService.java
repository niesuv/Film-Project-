package niesuv.project.film.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import niesuv.project.film.EmbeddedType.ROLE;
import niesuv.project.film.customException.UserNameExistException;
import niesuv.project.film.customException.CredentialsException;
import niesuv.project.film.dto.UserSigninDTO;
import niesuv.project.film.dto.UserSignupDTO;
import niesuv.project.film.entity.UserDetails;
import niesuv.project.film.repository.UserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserDetails newUser(UserSignupDTO dto) {
        UserDetails userDetails = mapper.map(dto, UserDetails.class);
        userDetails.setRole(ROLE.USER);
        if (userRepository.existsByUsername(userDetails.getUsername())) {
            throw new UserNameExistException("UserName have exist");
        }
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userRepository.save(userDetails);
        return userDetails;
    }

    @Transactional
    public UserDetails signin(UserSigninDTO dto) {
        UserDetails userDetails = userRepository.findByUsername(dto.getUsername());
        if (userDetails == null)
            throw new CredentialsException("Username dont exists");
        if (passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            return userDetails;
        }
        else {
            throw new CredentialsException("Password not correct");
        }

    }
}
