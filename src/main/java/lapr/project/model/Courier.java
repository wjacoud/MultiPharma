package lapr.project.model;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class Courier {
    
    private Long id;
    private final String ename;
    private final long nif;
    private final long niss;
    private final long weight;
    private final User user;

    public Courier(Long id, String ename, long nif, long niss, long weight, String emailUser, String passUser) {
        this.id = id;
        this.ename = ename;
        this.nif = nif;
        this.niss = niss;
        this.weight = weight;
        this.user = new User(emailUser, passUser);
    }

    public long getId() {
        return this.id;
    }

    public String getEname() {
        return this.ename;
    }

    public long getNif() {
        return this.nif;
    }

    public long getNiss() {
        return this.niss;
    }

    public long getWeight() {
        return this.weight;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
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

        final Courier other = (Courier) obj;

        return this.id.equals(other.getId());
    }
    
    @Override
    public String toString() {
        return "Courier{" + "id=" + id + ", ename=" + ename + ", nif=" + nif + ", niss=" + niss + ", weight=" + weight + ", user=" + user + '}';
    }
}
