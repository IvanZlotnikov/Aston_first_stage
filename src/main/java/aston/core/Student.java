package aston.core;

// groupNumber, averageScore, recordBookNumber

class Student {
    private final String groupNumber;
    private final double averageScore;
    private final String recordBookNumber;

    private Student(String groupNumber, double averageScore, String recordBookNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.recordBookNumber = recordBookNumber;
    }

    public String getgroupNumber() {
        return groupNumber;
    }

    public double getaverageScore() {
        return averageScore;
    }

    public String getrecordBookNumber() {
        return recordBookNumber;
    }

    // Builder класс
    public static class StudentBuilder {
        private String groupNumber;
        private double averageScore;
        private String recordBookNumber;

        public StudentBuilder groupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder averageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public StudentBuilder recordBookNumber(String recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        Student build() {
            if (groupNumber == null || groupNumber.isEmpty()) {
                throw new IllegalArgumentException("Номер группы не может быть пустым");
            }

            if (averageScore <= 0) {
                throw new IllegalArgumentException("Средний балл должен быть больше нуля!");
            }
            if (recordBookNumber == null || recordBookNumber.isEmpty()) {
                throw new IllegalArgumentException("Номер зачетной книжки не может быть пустым");
            }
            return new Student(groupNumber, averageScore, recordBookNumber);
        }
    }
}
