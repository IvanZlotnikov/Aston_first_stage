package aston.model;

import aston.utils.UtilsToProject;
import aston.strategy.SearchStrategy;
import aston.strategy.SortStrategy;


//представление пользователя
public class User implements Comparable<User>, SortStrategy<User>, SearchStrategy<User> {
    private final String name;
    private final String password;
    private final String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Пользователь: %s | Email: %s | Пароль: %s", name, email, password);
    }

    public static class Builder {
        private String name;
        private String password;
        private String email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
    @Override
    public int compareTo(User target) {
        if (target == null)
            throw new IllegalArgumentException("target is null");
        return this.name.compareToIgnoreCase(target.name);
    }
    @Override
    public int searchFor(User[] array, User target) {
        return UtilsToProject.search(array, target);
    }

    @Override
    public void sort(User[] array) {
        UtilsToProject.sort(array);
    }

}
