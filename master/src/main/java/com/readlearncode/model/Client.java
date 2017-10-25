package com.readlearncode.model;

import javax.inject.Named;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Named
public class Client {

    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotNull
    private String email;

    @Past
    @NotNull
    private Date dob; // Date Of Birth

    private Boolean acceptTOS; // Have the Terms Of Service been accepted

    public Client() {
    }

    public Client(Long id, String name, Date dob, String email, Boolean acceptTOS) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.acceptTOS = acceptTOS;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAcceptTOS() {
        return acceptTOS;
    }

    public void setAcceptTOS(Boolean acceptTOS) {
        this.acceptTOS = acceptTOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(email, client.email) &&
                Objects.equals(dob, client.dob) &&
                Objects.equals(acceptTOS, client.acceptTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, dob, acceptTOS);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", acceptTOS=" + acceptTOS +
                '}';
    }
}