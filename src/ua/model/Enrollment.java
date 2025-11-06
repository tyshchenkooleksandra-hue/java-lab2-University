package ua.model;

import ua.util.ValidationHelper;

import java.time.LocalDate;
import java.util.Objects;

public class Enrollment {
    private Person person;
    private Course course;
    private LocalDate enrollmentDate;

    public Enrollment(Person person, Course course, LocalDate enrollmentDate) {
        ValidationHelper.requireNonNull(person, "Person");
        ValidationHelper.requireNonNull(course, "Course");

        this.person = person;
        this.course = course;
        this.enrollmentDate = enrollmentDate != null ? enrollmentDate : LocalDate.now();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        ValidationHelper.requireNonNull(person, "Person");
        this.person = person;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        ValidationHelper.requireNonNull(course, "Course");
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        ValidationHelper.requireDateNotNull(enrollmentDate, "Enrollment date");
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Enrollment{");
        sb.append("person=").append(person);
        sb.append(", course=").append(course.getTitle());
        sb.append(", enrollmentDate=").append(enrollmentDate);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Enrollment)) return false;
        Enrollment e = (Enrollment) other;
        return Objects.equals(person, e.person)
                && Objects.equals(course, e.course)
                && Objects.equals(enrollmentDate, e.enrollmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, course, enrollmentDate);
    }

    public static Enrollment of(Person person, Course course) {
        return new Enrollment(person, course, LocalDate.now());
    }
}
