package aston.entity;

public class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student target) {
        return Integer.compare(this.groupNumber, target.groupNumber);
    }

    private int groupNumber;
    private double averageGrade;
    private int numberOfRecordBook;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageGrade = builder.averageGrade;
        this.numberOfRecordBook = builder.numberOfRecordBook;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getNumberOfRecordBook() {
        return numberOfRecordBook;
    }

    public static class Builder {
        private int groupNumber;
        private double averageGrade;
        private int numberOfRecordBook;

        public Builder setGroupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder setAverageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder setNumberOfRecordBook(int numberOfRecordBook) {
            this.numberOfRecordBook = numberOfRecordBook;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}