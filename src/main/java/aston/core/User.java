package aston.core;

class User {
    private final String name;
    private final String password;
    private final String email;

    private User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getname() {
        return name;
    }

    public String getpassword() {
        return password;
    }

    public String getemail() {
        return email;
    }

    // Builder класс
    public static class UserBuilder {
        private String name;
        private String password;
        private String email;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        User build() {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Имя пользователя не может быть пустым");
            }

            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Пароль не может быть пустым");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("E-mail не может быть пустым");
            }
            return new User(name, password, email);
        }
    }
}
