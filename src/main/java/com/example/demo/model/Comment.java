package com.example.demo.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "author_id")
  @NotNull
  private User author;

  @NotNull
  private String text;

  @Column(name = "created_at")
  @NotNull
  private OffsetDateTime createdAt;

  public Long getId() {
    return id;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
