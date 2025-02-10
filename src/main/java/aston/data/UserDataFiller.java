package aston.data;

import aston.core.DataFiller;
import aston.model.User;
import aston.utils.FileReaderUtill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataFiller implements DataFiller<User> {
    private final String filePath;

    public UserDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public User[] fillData() {
        String[] lines = FileReaderUtill.readFile(filePath);
        User[] users = new User[lines.length];
        Pattern pattern = Pattern.compile("(\\d+) (\\w+) (\\d+)");
        for (int i = 0; i < lines.length; i++) {
            Matcher matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                users[i] = new User.Builder()
                        .setName(matcher.group(0))
                        .setPassword(matcher.group(1))
                        .setEmail(matcher.group(2))
                        .build();
            }
        }
        return users;
    }
}
