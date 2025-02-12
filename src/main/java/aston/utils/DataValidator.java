package aston.utils;

// валидация данных
public class DataValidator {

    public boolean validate(String data) {
        return data != null && !data.isEmpty();
    }
}
