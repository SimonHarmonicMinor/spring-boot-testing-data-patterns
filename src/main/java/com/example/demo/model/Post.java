package com.example.demo.model;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post")
public class Post {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "post_id")
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String content;

  @NotNull
  private double rating;

  @OneToMany(fetch = LAZY, mappedBy = "post", cascade = {PERSIST, MERGE})
  @NotNull
  private List<Comment> comments = new ArrayList<>();

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "author_id")
  @NotNull
  private User author;

  @Column(name = "created_at")
  @NotNull
  private OffsetDateTime createdAt;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Post post = (Post) o;
    return Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return 2022;
  }

  public Post(String name, String content, double rating, User author,
      OffsetDateTime createdAt) {
    this.name = name;
    this.content = content;
    this.rating = rating;
    this.author = author;
    this.createdAt = createdAt;
  }

  public Post() {
  }
}
