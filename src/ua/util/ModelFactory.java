package ua.util;
import ua.model.*;
import ua.model.enums.*;

import java.time.LocalDate;
import java.util.logging.Logger; // Імпорт

public class ModelFactory {

    private static final Logger LOGGER = Logger.getLogger(ModelFactory.class.getName());

    private ModelFactory() {
    }

    public static Professor createProfessor(String firstName, String lastName, Departments department) {
        ValidationHelper.requireNonBlank(firstName, "Professor first name");
        ValidationHelper.requireNonBlank(lastName, "Professor last name");
        ValidationHelper.requireNonNull(department, "Department");

        LOGGER.info("Creating Professor: " + firstName + " " + lastName + " [" + department + "]");
        return new Professor(firstName, lastName, department);
    }

    public static Student createStudent(String firstName, String lastName) {
        ValidationHelper.requireNonBlank(firstName, "Student first name");
        ValidationHelper.requireNonBlank(lastName, "Student last name");

        LOGGER.info("Creating Student: " + firstName + " " + lastName);
        return new Student(firstName, lastName);
    }

    public static Course createCourse(String title, int credits, int duration, ExamType examType) {
        ValidationHelper.requireNonBlank(title, "Course title");
        ValidationHelper.requirePositive(credits, "Course credits");
        ValidationHelper.requirePositive(duration, "Course duration");
        ValidationHelper.requireNonNull(examType, "Exam type");

        LOGGER.info("Creating Course: " + title);
        return new Course(title, credits, duration, examType);
    }
}