package aston.model;

public class Student implements Comparable<Object> {
    
    private final String groupNumber;
    private final double averageScore;
    private final String recordBookNumber;

    public Student(String groupNumber, double averageScore, String recordBookNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.recordBookNumber = recordBookNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
