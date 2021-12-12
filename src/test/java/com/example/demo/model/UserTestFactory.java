package com.example.demo.model;

public class UserTestFactory {

  public static User createUser(String login) {
    return createUser(login, "", "");
  }

  public static User createUser(String login, String firstName, String lastName) {
    final var user = new User();
    user.setLogin(login);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    return user;
  }
}