package model;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  this.name + " "+this.surname;
    }
}
