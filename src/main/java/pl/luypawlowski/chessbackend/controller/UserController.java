package pl.luypawlowski.chessbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.luypawlowski.chessbackend.exception.UserExistsException;
import pl.luypawlowski.chessbackend.model.NewEmailRequest;
import pl.luypawlowski.chessbackend.model.NewLoginRequest;
import pl.luypawlowski.chessbackend.model.NewPasswordRequest;
import pl.luypawlowski.chessbackend.model.UserDto;
import pl.luypawlowski.chessbackend.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserDto userDto) {
        try{
            return ResponseEntity.ok(userService.saveUser(userDto));
        } catch (UserExistsException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getUserByLogin(@RequestParam("login") String login) {
        try {
            return ResponseEntity.ok(userService.getUserByLogin(login));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(login + " not found!");
        }
    }

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping
    public void deleteUserByLogin(@RequestParam("login") String login) {
        userService.deleteUserByLogin(login);
    }

    @PutMapping("/{id}/new-password")
    public UserDto editUserPassword(@RequestBody NewPasswordRequest newPasswordRequest, @PathVariable Long id){
        return userService.updateUserPassword(newPasswordRequest.getPassword(), id);
    }

    @PutMapping("/{id}/new-login")
    public UserDto editUserLogin(@RequestBody NewLoginRequest newLoginRequest, @PathVariable Long id){
        return userService.updateUserLogin(newLoginRequest.getLogin(), id);
    }

    @PutMapping("/{id}/new-email")
    public UserDto editUserEmail(@RequestBody NewEmailRequest newEmailRequest, @PathVariable Long id){
        return userService.updateUserEmail(newEmailRequest.getEmail(), id);
    }
}
