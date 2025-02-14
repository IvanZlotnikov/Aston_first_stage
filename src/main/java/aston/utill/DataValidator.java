package aston.utill;

//Общие методы валидации данных.
public class DataValidator {

    public static boolean validateBusData(int number, String model, int mileage) {
        return validateNumber(number) && validateString(model) && mileage >= 0;
    }

    public static boolean validateStudentData(int groupNumber, double averageGrade, int numberOfRecordBook) {
        return validateNumber(groupNumber) && averageGrade >= 0 && validateNumber(numberOfRecordBook);
    }

    public static boolean validateUserData(String name,int password,String email) {
        return validateString(name) &&  validateNumber(password) && validateString(email);
    }

    public static boolean validateNumber(int number) {
        return number > 0;
    }

    public static boolean validateString(String string) {
        return string != null && !string.isEmpty();
    }
}
