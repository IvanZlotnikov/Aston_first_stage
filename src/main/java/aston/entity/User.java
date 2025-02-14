package aston.entity;

public class User implements Comparable<User> {
    @Override
    public int compareTo(User target) {
        return Integer.compare(this.password,target.password);
    }

    private String name;
    private int password;
    private String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String name;
        private int password;
        private String email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(int password) {
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
}