package pl.luypawlowski.chessbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.luypawlowski.chessbackend.entities.User;
import pl.luypawlowski.chessbackend.model.UserDto;
import pl.luypawlowski.chessbackend.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public Long saveUser(UserDto userDto) {
        User user = userDto.toDomain();
        User save = usersRepository.save(user);

        return save.getId();
    }

    public UserDto getUserByLogin(String login) {
        return UserDto.fromDomain(usersRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found!")));
    }

    public List<UserDto> getAllUsers() {
        return usersRepository.findAll().stream().map(UserDto::fromDomain).collect(Collectors.toList());
    }

    public void deleteUserByLogin(String login) {
        User user = usersRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found!"));
        usersRepository.delete(user);
    }

    public UserDto updateUserLogin(String newLogin, Long id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setLogin(newLogin);

        return UserDto.fromDomain(user);
    }

    public UserDto updateUserEmail(String newEmail, Long id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setEmail(newEmail);

        return UserDto.fromDomain(user);
    }

    public UserDto updateUserPassword(String newPassword, Long id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setPassword(newPassword);

        return UserDto.fromDomain(user);
    }
}
