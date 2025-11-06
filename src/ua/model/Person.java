package ua.model;

import ua.util.ValidationHelper;

import java.util.Objects;

public abstract class Person extends BaseEntity {
    protected String firstName;
    protected String lastName;

    public Person(String firstName, String lastName) {
        super();
        ValidationHelper.requireNonBlank(firstName, "First name");
        ValidationHelper.requireNonBlank(lastName, "Last name");

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        ValidationHelper.requireNonBlank(firstName, "First name");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        ValidationHelper.requireNonBlank(lastName, "Last name");
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(' ').append(lastName);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Person)) return false;
        Person p = (Person) other;
        return Objects.equals(firstName, p.firstName)
                && Objects.equals(lastName, p.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
