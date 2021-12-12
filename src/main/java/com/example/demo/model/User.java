package com.example.demo.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_table")
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @NotNull
  private String login;

  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Column(name = "last_name")
  private String lastName;

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User(String login, String firstName, String lastName) {
    this.login = login;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User() {
  }
}
