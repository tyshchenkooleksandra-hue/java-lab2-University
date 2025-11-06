package ua.model;

import ua.util.ValidationHelper;
import ua.model.enums.Departments;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Professor extends Person {

    private Departments department;
    private final List<Course> courses = new ArrayList<>();

    public Professor(String firstName, String lastName, Departments department) {
        super(firstName, lastName);
        ValidationHelper.requireNonNull(department, "Department");
        this.department = department;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        ValidationHelper.requireNonNull(department, "Department");
        this.department = department;
    }

    public List<Course> getCourses() {
        return List.copyOf(courses);
    }

    public void addCourse(Course course) {
        ValidationHelper.requireNonNull(course, "Course");
        if (!courses.contains(course)) {
            courses.add(course);
            course.assignProfessor(this);
        }
    }

    public void removeCourse(Course course) {
        if (courses.remove(course)) {
            course.assignProfessor(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Professor{");
        sb.append("name='").append(firstName).append(' ').append(lastName).append('\'');
        sb.append(", department=").append(department);
        sb.append(", coursesCount=").append(courses.size());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Professor)) return false;
        if (!super.equals(other)) return false;
        Professor p = (Professor) other;
        return department == p.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
