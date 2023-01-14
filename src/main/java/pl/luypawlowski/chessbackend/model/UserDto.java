package pl.luypawlowski.chessbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.luypawlowski.chessbackend.entities.User;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String email;

    public User toDomain() {
        return new User(this.id, this.login, this.password, this.email);
    }

    public static UserDto fromDomain(User user) {
        return new UserDto(user.getId(),user.getLogin(),user.getPassword(),user.getEmail());
    }

}
