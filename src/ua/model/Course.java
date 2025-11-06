package ua.model;

import ua.model.enums.ExamType;
import ua.util.ValidationHelper;

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
        ValidationHelper.requireNonBlank(title, "Course title");
        ValidationHelper.requirePositive(credits, "Course credits");
        ValidationHelper.requirePositive(duration, "Course duration");
        ValidationHelper.requireNonNull(examType, "Exam type");

        this.title = title;
        this.credits = credits;
        this.duration = duration;
        this.examType = examType;
    }

    public Course(String title, int credits, int duration) {
        this(title, credits, duration, ExamType.FINAL);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        ValidationHelper.requireNonBlank(title, "Course title");
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        ValidationHelper.requirePositive(credits, "Course credits");
        this.credits = credits;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        ValidationHelper.requirePositive(duration, "Course duration");
        this.duration = duration;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        ValidationHelper.requireNonNull(examType, "Exam type");
        this.examType = examType;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void assignProfessor(Professor professor) {
        this.professor = professor;
    }

    public void addEnrollment(Enrollment enrollment) {
        ValidationHelper.requireNonNull(enrollment, "Enrollment");
        enrollments.add(enrollment);
    }

    public List<Enrollment> getEnrollments() {
        return List.copyOf(enrollments);
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
            sb.append(professor.getLastName())
                    .append('(').append(professor.getId()).append(')');
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
        if (!(other instanceof Course course)) return false;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
