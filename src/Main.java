import ua.model.enums.ExamType;
import ua.records.ExamRecord;
import ua.util.ModelFactory;
import ua.model.*;
import ua.model.enums.Departments;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Professor prof = ModelFactory.createProfessor("Elena", "Ivanova", Departments.COMPUTER_SCIENCE);

        Course oop = ModelFactory.createCourse("OOP in Java", 5, 30);
        Course db = ModelFactory.createCourse("Databases", 4, 25);

        prof.addCourse(oop);
        prof.addCourse(db);

        Student s1 = ModelFactory.createStudent("Ivan", "Petrov");
        Student s2 = ModelFactory.createStudent("Maria", "Sidorova");

        s1.enroll(oop);
        s2.enroll(oop);
        s1.enroll(db);

        System.out.println("\n--- PROFESSOR ---");
        System.out.println(prof);

        System.out.println("\n--- COURSES ---");
        System.out.println(oop);
        System.out.println(db);

        System.out.println("\n--- STUDENTS ---");
        System.out.println(s1);
        System.out.println(s2);

        System.out.println("\n--- Enrollments for OOP ---");
        oop.getEnrollments().forEach(System.out::println);

        System.out.println("\n--- Enrollments for DB ---");
        db.getEnrollments().forEach(System.out::println);

        ExamRecord exam1 = ExamRecord.of(oop, ExamType.FINAL, LocalDate.of(2025, 6, 15));
        ExamRecord exam2 = ExamRecord.of(db, ExamType.QUIZ, LocalDate.of(2025, 3, 10));

        System.out.println(exam1);
        System.out.println(exam2);
    }
}
