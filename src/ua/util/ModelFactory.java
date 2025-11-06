package ua.util;
import ua.model.*;
import ua.model.enums.*;

import java.time.LocalDate;

public class ModelFactory {

    private ModelFactory() {
    }

    public static Professor createProfessor(String firstName, String lastName, Departments department) {
        ValidationHelper.requireNonBlank(firstName, "Professor first name");
        ValidationHelper.requireNonBlank(lastName, "Professor last name");
        ValidationHelper.requireNonNull(department, "Department");
        return new Professor(firstName, lastName, department);
    }

    public static Student createStudent(String firstName, String lastName) {
        ValidationHelper.requireNonBlank(firstName, "Student first name");
        ValidationHelper.requireNonBlank(lastName, "Student last name");
        return new Student(firstName, lastName);
    }

    public static Course createCourse(String title, int credits, int duration, ExamType examType) {
        ValidationHelper.requireNonBlank(title, "Course title");
        ValidationHelper.requirePositive(credits, "Course credits");
        ValidationHelper.requirePositive(duration, "Course duration");
        ValidationHelper.requireNonNull(examType, "Exam type");

        return new Course(title, credits, duration, examType);
    }

    public static Enrollment createEnrollment(Student student, Course course) {
        ValidationHelper.requireNonNull(student, "Student");
        ValidationHelper.requireNonNull(course, "Course");
        return new Enrollment(student, course, LocalDate.now());
    }

    public static Enrollment createEnrollment(Student student, Course course, LocalDate date) {
        ValidationHelper.requireNonNull(student, "Student");
        ValidationHelper.requireNonNull(course, "Course");
        ValidationHelper.requireDateNotNull(date, "Enrollment date");
        return new Enrollment(student, course, date);
    }
}