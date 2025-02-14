package aston.handler;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;

import java.io.FileWriter;
import java.io.IOException;

//Запись данных в файлы.
public class FileManager {

    public static void writeToFile(String data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static<T> String buildDataString(T element) {
        StringBuilder data = new StringBuilder();
        if (element instanceof Bus) {
            Bus bus = (Bus) element;
            data.append("Номер: ").append(bus.getNumber())
                    .append(", Модель: ").append(bus.getModel())
                    .append(", Пробег: ").append(bus.getMileage())
                    .append("\n");
        } else if (element instanceof Student) {
            Student student = (Student) element;
            data.append("Номер группы: ").append(student.getGroupNumber())
                    .append(", Средний балл: ").append(student.getAverageGrade())
                    .append(", Номер зачетной книжки: ").append(student.getNumberOfRecordBook())
                    .append("\n");
        } else if (element instanceof User) {
            User user = (User) element;
            data.append("Имя: ").append(user.getName())
                    .append(", Пароль: ").append(user.getPassword())
                    .append(", Почта: ").append(user.getEmail())
                    .append("\n");
        }
        return data.toString();
    }
}
