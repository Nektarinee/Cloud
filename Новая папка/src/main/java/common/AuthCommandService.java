package common;
import java.io.Serializable;
public class AuthCommandService implements Serializable
{
    private final String login;
    private final String password;

    public AuthCommandService(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
