package ua.model;

import ua.util.ValidationHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level; // Імпорт
import java.util.logging.Logger; // Імпорт

public class Student extends Person {

    private static final Logger LOGGER = Logger.getLogger(Student.class.getName());

    private final List<Enrollment> enrollments = new ArrayList<>();

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public List<Enrollment> getEnrollments() {
        return List.copyOf(enrollments);
    }

    public Enrollment enroll(Course course) {
        ValidationHelper.requireNonNull(course, "Course");

        boolean alreadyEnrolled = enrollments.stream()
                .anyMatch(e -> e.getCourse().equals(course));

        if (alreadyEnrolled) {
            String errorMsg = "Student " + getFirstName() + " is already enrolled in " + course.getTitle();
            LOGGER.log(Level.WARNING, errorMsg);
            throw new IllegalStateException(errorMsg);
        }

        Enrollment enrollment = new Enrollment(this, course, LocalDate.now());
        enrollments.add(enrollment);
        course.addEnrollment(enrollment);

        LOGGER.info("Student " + getFirstName() + " successfully enrolled in " + course.getTitle());
        return enrollment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(firstName).append(' ').append(lastName).append('\'');
        sb.append(", enrollments=").append(enrollments.size());
        sb.append('}');
        return sb.toString();
    }
}