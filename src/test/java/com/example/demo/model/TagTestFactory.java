package com.example.demo.model;

public class TagTestFactory {

  public static Tag createTag(String name) {
    final var tag = new Tag();
    tag.setName(name);
    return tag;
  }
}