package aston.filesReader;

import aston.model.Bus;
import aston.model.Student;
import aston.model.User;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadeFromFile {
    java.io.FileReader reader;
    java.util.Scanner scanner;
    public List<Bus> readFromBus(){
        List<Bus> busList=new ArrayList<>();
        Pattern p=Pattern.compile("([\\\\w]+) ([\\\\w]+) ([\\\\w]+)");

            try {
                reader= new FileReader("\"src/main/aston/files/for_bus\"");
                scanner = new Scanner(reader);
                while (scanner.hasNextLine()){
                    String s =scanner.nextLine();
                    Matcher m=p.matcher(s);
                    while (m.find()){
                        busList.add(new Bus
                                .Builder()
                                .setNumber(m.group(1))
                                .setModel(m.group(2))
                                .setMileage(Integer.parseInt(m.group(3)))
                                .build());
                    }
                }
                reader.close();
            }catch (Exception e) {
            }
            return busList ;
    }
    public List<Student> readFromStudent(){
        List<Student> studentList=new ArrayList<>();
        Pattern p=Pattern.compile("([\\\\w]+) ([\\\\w]+) ([\\\\w]+)");

        try {
            reader= new FileReader("\"src/main/aston/files/for_student\"");
            scanner = new Scanner(reader);
            while (scanner.hasNextLine()){
                String s =scanner.nextLine();
                Matcher m=p.matcher(s);
                while (m.find()){
                    studentList.add(new Student
                            .Builder()
                            .setGroupNumber(Integer.parseInt(m.group(1)))
                            .setRecordBookNumber((Integer.parseInt(m.group(2))))
                            .setAverageGrade(Double.parseDouble(m.group(3)))
                            .build());
                }
            }
            reader.close();
        }catch (Exception e) {
        }
        return studentList ;
    }
    public List<User> readFromUser(){
        List<User> userList=new ArrayList<>();
        Pattern p=Pattern.compile("([\\\\w]+) ([\\\\w]+) ([\\\\w]+)");

        try {
            reader= new FileReader("\"src/main/aston/files/for_user\"");
            scanner = new Scanner(reader);
            while (scanner.hasNextLine()){
                String s =scanner.nextLine();
                Matcher m=p.matcher(s);
                while (m.find()){
                    userList.add(new User.Builder()
                            .setName(m.group(1))
                            .setPassword(m.group(2))
                            .setEmail(m.group(3))
                            .build());
                }
            }
            reader.close();
        }catch (Exception e) {
        }
        return userList ;
    }
}
