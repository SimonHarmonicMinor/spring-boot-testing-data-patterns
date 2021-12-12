package com.example.demo.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "tag_id")
  private Long id;

  @NotNull
  private String name;

  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "post_tag",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "post_id")
  )
  private Set<Post> posts = new HashSet<>();

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tag tag = (Tag) o;
    return Objects.equals(id, tag.id);
  }

  @Override
  public int hashCode() {
    return 2022;
  }

  public Tag(String name) {
    this.name = name;
  }
}
