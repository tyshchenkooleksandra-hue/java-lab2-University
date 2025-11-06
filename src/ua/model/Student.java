package ua.model;

import ua.util.ValidationHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
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
            throw new IllegalStateException("Student is already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(this, course, LocalDate.now());
        enrollments.add(enrollment);
        course.addEnrollment(enrollment);
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
