package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.AbstractIntegrationTest;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostRepositoryTest extends AbstractIntegrationTest {
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TagRepository tagRepository;

  @Test
  @DisplayName("Should return top 3 posts")
  void shouldReturnTop3Posts() {
    final var user = userRepository.save(new User("login", "", ""));
    for (int i = 0; i < 10; i++) {
      final var post = postRepository.save(
          new Post("name" + i, "content", i, user, OffsetDateTime.now())
      );
      commentRepository.saveAll(
          List.of(
              new Comment(user, "comment1", OffsetDateTime.now(), post),
              new Comment(user, "comment2", OffsetDateTime.now(), post)
          )
      );
    }

    final var res = postRepository.findTopPosts(3);

    assertEquals(3, res.size(), "Unexpected posts count");
    assertTrue(
        res.stream()
            .allMatch(post -> post.comments().size() == 2),
        "Each post should have 2 comments"
    );
  }
}