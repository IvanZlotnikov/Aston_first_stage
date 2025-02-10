package aston.model;

import aston.utils.UtilsToProject;
import aston.strategy.SearchStrategy;
import aston.strategy.SortStrategy;


public class Student implements Comparable<Student>, SortStrategy<Student>, SearchStrategy<Student> {

    private final int groupNumber;
    private final double averageGrade;
    private final int recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageGrade = builder.averageGrade;
        this.recordBookNumber = builder.recordBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
               "groupNumber=" + groupNumber +
               ", averageGrade=" + averageGrade +
               ", recordBookNumber=" + recordBookNumber +
               '}';
    }

    @Override
    public int compareTo(Student target) {
        if (target == null)
            throw new IllegalArgumentException("target is null");
        return Double.compare(this.averageGrade, target.averageGrade);
    }

    @Override
    public int searchFor(Student[] array, Student target) {
        return UtilsToProject.search(array, target);
    }

    @Override
    public void sort(Student[] array) {
        UtilsToProject.sort(array);
    }

    public static class Builder {
        private int groupNumber;
        private double averageGrade;
        private int recordBookNumber;

        public Builder setGroupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder setAverageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder setRecordBookNumber(int recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
