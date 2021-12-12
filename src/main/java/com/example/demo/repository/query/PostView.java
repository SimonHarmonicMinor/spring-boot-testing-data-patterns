package com.example.demo.repository.query;

import java.util.List;
import java.util.Set;

public record PostView(
    Long id,
    String name,
    double rating,
    String userLogin,
    List<CommentView> comments,
    Set<TagView> tags
) {

}
