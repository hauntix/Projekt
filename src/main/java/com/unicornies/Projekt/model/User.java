package com.unicornies.Projekt.model;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userID")
    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private Set address;

    public User(){

    }
    public User(Long userID, String firstName, String lastName, String email, Set address) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public Set getAddress() {
        return address;
    }

    public void setAddress(Set address) {
        this.address = address;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
