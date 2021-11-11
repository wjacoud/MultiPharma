package lapr.project.model;

/**
 * @author 
 */
public class User {

    private final String email;   // Primary Key
    private final String pass;

    public User(final String email, final String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPass() {
        return this.pass;
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;

        return this.email.equals(other.getEmail());
    }

    @Override
    public String toString() {
        return "User{" + "email=" + this.email + ", pass=" + this.pass + '}';
    }
    
}