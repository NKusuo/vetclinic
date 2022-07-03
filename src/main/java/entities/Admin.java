package entities;

public class Admin {

    private Integer idAdmin;
    private String login;
    private String password;

    public Admin(String login, String password) {
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