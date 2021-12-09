package at.models;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @SerializedName("Алиас пользователя")
    private String alias;
    @SerializedName("Логин пользователя")
    private String login;
    @SerializedName("Пароль пользователя")
    private String password;
    @SerializedName("Роль пользователя")
    private String role;
    @SerializedName("Имя пользователя")
    private String name;

    public User(String alias, String login, String password, String role) {
        this.alias = alias;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }
}
