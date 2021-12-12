package com.example.demo.model;

import static java.util.stream.Collectors.toSet;

import com.example.demo.test_util.Builder;
import java.util.ArrayList;
import java.util.List;

public class TagTestBuilder implements Builder<Tag> {
  private String name = "tag_name";
  private List<Builder<Post>> posts = new ArrayList<>();

  public static TagTestBuilder aTag() {
    return new TagTestBuilder();
  }

  private TagTestBuilder(TagTestBuilder builder) {
    this.name = builder.name;
    this.posts = new ArrayList<>(builder.posts);
  }

  private TagTestBuilder() {
  }

  public TagTestBuilder withName(String name) {
    final var copy = new TagTestBuilder(this);
    copy.name = name;
    return copy;
  }

  public TagTestBuilder withPost(Builder<Post> post) {
    return withPosts(List.of(post));
  }

  public TagTestBuilder withPosts(List<? extends Builder<Post>> posts) {
    final var copy = new TagTestBuilder();
    copy.posts = new ArrayList<>(posts);
    return copy;
  }

  @Override
  public Tag build() {
    final var tag = new Tag();
    tag.setName(name);
    tag.setPosts(
        posts.stream()
            .map(Builder::build)
            .collect(toSet())
    );
    return tag;
  }
}
