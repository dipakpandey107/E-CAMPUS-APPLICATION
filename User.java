public class User {
    String username;
    String password;
    String fullName;

    public User(String uname, String passwd, String fullName) {
        this.username = uname;
        this.password = passwd;
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }
}