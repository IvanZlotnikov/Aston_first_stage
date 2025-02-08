package aston.data;

import aston.core.DataFiller;
import aston.model.User;

public class UserDataFiller implements DataFiller<User> {
    @Override
    public User[] fillData() {
        return new User[]{
                new User.Builder().setName("User1").setPassword("password1").setEmail("user1@gmail.com").build(),
                new User.Builder().setName("User2").setPassword("password2").setEmail("user2@gmail.com").build(),
                new User.Builder().setName("User3").setPassword("password3").setEmail("user3@gmail.com").build(),
        };
    }
}
