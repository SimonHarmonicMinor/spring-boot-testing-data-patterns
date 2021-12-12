package com.example.demo.repository.query;

import java.util.List;

public record PostView(
    Long id,
    String name,
    double rating,
    String userLogin,
    List<CommentView> comments
) {

}
