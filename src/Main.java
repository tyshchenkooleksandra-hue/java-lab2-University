import ua.model.Course;
import ua.model.Professor;
import ua.model.Student;
import ua.service.DataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static final Logger LOGGER = Logger.getLogger("ua");

    static {
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
    }

    public static void main(String[] args) {

        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        LOGGER.info("Application starting...");

        DataService dataService = new DataService();
        List<Professor> professors = null;
        List<Student> students = null;
        List<Course> courses = null;

        try {
            professors = dataService.loadProfessors("professors.csv");
            students = dataService.loadStudents("students.csv");
            courses = dataService.loadCourses("courses.csv");

            LOGGER.info("All data loaded successfully. Check WARNINGS for skipped lines.");

            if (professors.isEmpty() || students.isEmpty() || courses.isEmpty()) {
                LOGGER.severe("Not enough data to run simulation. Exiting.");
                return;
            }

            Professor prof = professors.get(0);
            Student s1 = students.get(0);
            Student s2 = students.get(1);
            Course oop = courses.get(0);
            Course db = courses.get(1);

            prof.addCourse(oop);
            prof.addCourse(db);

            try {
                s1.enroll(oop);
                s2.enroll(oop);
                s1.enroll(db);

                LOGGER.info("--- Attempting to enroll " + s1.getFirstName() + " in " + oop.getTitle() + " again... ---");
                s1.enroll(oop);

            } catch (IllegalStateException e) {
                LOGGER.log(Level.SEVERE, "Logical Error caught: " + e.getMessage());
            }

            System.out.println("\n--- FINAL STATE ---");
            System.out.println(prof);
            System.out.println(oop);
            System.out.println(db);
            System.out.println(s1);
            System.out.println(s2);

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "FATAL: Data file not found. " + e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "FATAL: Failed to read data file. " + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected application error occurred.", e);
        } finally {
            LOGGER.info("Application shutting down.");
        }
    }
}