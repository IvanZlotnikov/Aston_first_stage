package aston.data;

import aston.core.DataFiller;
import aston.model.User;
import aston.utils.FileReaderUtill;

public class UserDataFiller implements DataFiller<User> {
    private String filePath;

    public UserDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public User[] fillData() {
        String[] lines = FileReaderUtill.readFile(filePath);
        User[] users = new User[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] splits = lines[i].split(",");
            users[i] = new User.Builder()
                    .setName(splits[0])
                    .setPassword(splits[1])
                    .setEmail(splits[2])
                    .build();
        }
        return users;
    }
}
