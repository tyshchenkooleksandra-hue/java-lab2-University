package ua.model;

import ua.model.enums.ExamType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Course extends BaseEntity {
    private String title;
    private int credits;
    private int duration;
    private ExamType examType;
    private Professor professor;
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Course(String title, int credits, int duration, ExamType examType) {
        super();
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (credits <= 0) throw new IllegalArgumentException("Credits must be positive");
        if (duration <= 0) throw new IllegalArgumentException("Duration must be positive");
        if (examType == null) throw new IllegalArgumentException("ExamType cannot be null");

        this.title = title;
        this.credits = credits;
        this.duration = duration;
        this.examType = examType;
    }

    public Course(String title, int credits, int duration) {
        this(title, credits, duration, ExamType.FINAL);
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        if (examType == null) {
            throw new IllegalArgumentException("ExamType cannot be null");
        }
        this.examType = examType;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void assignProfessor(Professor professor) {
        this.professor = professor;
    }

    public void addEnrollment(Enrollment enrollment) {
        Objects.requireNonNull(enrollment, "Enrollment cannot be null");
        enrollments.add(enrollment);
    }

    public List<Enrollment> getEnrollments() {
        return List.copyOf(enrollments);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Course{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", credits=").append(credits);
        sb.append(", duration=").append(duration);
        sb.append(", examType=").append(examType);
        sb.append(", professor=");
        if (professor != null) {
            sb.append(professor.getLastName()).append('(').append(professor.getId()).append(')');
        } else {
            sb.append("none");
        }
        sb.append(", studentsCount=").append(enrollments.size());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Course)) return false;
        Course course = (Course) other;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}