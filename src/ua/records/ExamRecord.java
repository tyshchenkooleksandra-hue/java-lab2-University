package ua.records;

import ua.model.Course;
import ua.model.enums.ExamType;
import java.time.LocalDate;
import java.util.Objects;

public record ExamRecord(
        Course course,
        ExamType type,
        LocalDate date,
        double maxScore
) {
    public ExamRecord {
        Objects.requireNonNull(course, "Course cannot be null");
        Objects.requireNonNull(type, "ExamType cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");

        if (maxScore <= 0) {
            throw new IllegalArgumentException("Max score must be positive");
        }
    }


    public static ExamRecord of(Course course, ExamType type, LocalDate date) {
        double defaultMaxScore = switch (type) {
            case MIDTERM -> 70;
            case FINAL -> 100;
            case QUIZ -> 20;
            case PROJECT -> 150;
        };
        return new ExamRecord(course, type, date, defaultMaxScore);
    }
    public String examDescription() {
        return switch (type) {
            case MIDTERM -> "Проміжний іспит для перевірки поточних знань.";
            case FINAL -> "Фінальний іспит, що підсумовує курс.";
            case QUIZ -> "Короткий тест на основні теми.";
            case PROJECT -> "Практичний проєкт з реальним завданням.";
        };
    }

    @Override
    public String toString() {
        return new StringBuilder("ExamRecord{")
                .append("course='").append(course.getTitle()).append('\'')
                .append(", type=").append(type)
                .append(", date=").append(date)
                .append(", maxScore=").append(maxScore)
                .append(", description='").append(examDescription()).append('\'')
                .append('}')
                .toString();
    }
}
