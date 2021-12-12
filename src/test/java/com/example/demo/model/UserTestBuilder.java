package com.example.demo.model;

import com.example.demo.test_util.Builder;

public class UserTestBuilder implements Builder<User> {

  private String login = "login";
  private String firstName = "firstName";
  private String lastName = "lastName";

  public static UserTestBuilder aUser() {
    return new UserTestBuilder();
  }

  private UserTestBuilder(UserTestBuilder builder) {
    this.login = builder.login;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
  }

  private UserTestBuilder() {
  }

  public UserTestBuilder withLogin(String login) {
    final var copy = new UserTestBuilder(this);
    copy.login = login;
    return copy;
  }

  public UserTestBuilder withFirstName(String firstName) {
    final var copy = new UserTestBuilder(this);
    copy.firstName = firstName;
    return copy;
  }

  public UserTestBuilder withLastName(String lastName) {
    final var copy = new UserTestBuilder(this);
    copy.lastName = lastName;
    return copy;
  }

  @Override
  public User build() {
    final var user = new User();
    user.setLogin(login);
    user.setLastName(lastName);
    user.setFirstName(firstName);
    return user;
  }
}
