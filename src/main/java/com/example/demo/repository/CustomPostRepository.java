package com.example.demo.repository;

import com.example.demo.repository.query.PostView;
import java.util.List;

public interface CustomPostRepository {
  List<PostView> findTopPosts(int maxPostsCount);
}
