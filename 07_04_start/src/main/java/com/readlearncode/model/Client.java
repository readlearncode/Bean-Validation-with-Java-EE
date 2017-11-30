package com.readlearncode.model;

import com.readlearncode.model.contraints.EqualEmailParameters;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

@Named
@FacesConfig(version = JSF_2_3)
public class Client {

    private Integer id;

    @NotBlank(message = "Please ensure you enter your name")
    private String name;

    @Email(message = "You have entered an invalid email")
    @NotNull(message = "You must enter an email")
    private String email;

    @Email(message = "You have entered an invalid email")
    @NotNull(message = "You must enter an email")
    private String confirmEmail;

    @Past(message = "The date ${validatedValue} is in the future. Please enter your date of birth!")
    @NotNull(message = "You must enter a date")
    private Date dob; // Date Of Birth

    @AssertTrue(message = "You must accept the terms of service")
    private Boolean acceptTOS; // Have the Terms Of Service been accepted

    @CreditCardNumber(ignoreNonDigitCharacters = true)
    @NotNull(message = "You must enter a credit card number")
    private String creditCardNumber;

    public Client() {
    }

    @EqualEmailParameters
    public Client(Integer id, String name, Date dob, String email, String confirmEmail, Boolean acceptTOS, String creditCardNumber) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.acceptTOS = acceptTOS;
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
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

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Boolean getAcceptTOS() {
        return acceptTOS;
    }

    public void setAcceptTOS(Boolean acceptTOS) {
        this.acceptTOS = acceptTOS;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                ", dob=" + dob +
                ", acceptTOS=" + acceptTOS +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(email, client.email) &&
                Objects.equals(confirmEmail, client.confirmEmail) &&
                Objects.equals(dob, client.dob) &&
                Objects.equals(acceptTOS, client.acceptTOS) &&
                Objects.equals(creditCardNumber, client.creditCardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, confirmEmail, dob, acceptTOS, creditCardNumber);
    }
}