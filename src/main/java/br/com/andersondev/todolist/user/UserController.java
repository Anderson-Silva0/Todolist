package br.com.andersondev.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.andersondev.todolist.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserModel userModel) {

        var user = this.userRepository.findByUserName(userModel.getUserName());

        if (user != null) {
            throw  new BusinessException("Usuário já existe");
        }

        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
