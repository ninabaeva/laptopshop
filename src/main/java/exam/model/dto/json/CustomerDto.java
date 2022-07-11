package exam.model.dto.json;

import com.google.gson.annotations.Expose;
import exam.model.entity.Town;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CustomerDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private LocalDate registeredOn;
    @Expose
    private TownJsonDto town;

    public CustomerDto() {
    }

    @NotNull
    @Length(min = 2)
    public String getFirstName() {
        return firstName;
    }

    public CustomerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull
    @Length(min = 2)
    public String getLastName() {
        return lastName;
    }

    public CustomerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    public CustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotNull
    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public CustomerDto setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    public TownJsonDto getTown() {
        return town;
    }

    public CustomerDto setTown(TownJsonDto town) {
        this.town = town;
        return this;
    }
}
