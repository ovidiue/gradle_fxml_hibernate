import javax.persistence.*;

/**
 * Created by Ovidiu on 16-Apr-18.
 */
@Entity
public class Contact {
    @Column
    String name;
    @Column
    String surname;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact() {
    }
}
