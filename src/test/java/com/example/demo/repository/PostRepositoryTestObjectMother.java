package com.example.demo.repository;

import static com.example.demo.model.CommentTestFactory.createComment;
import static com.example.demo.model.PostTestFactory.createPost;
import static com.example.demo.model.UserTestFactory.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.test_util.AbstractIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostRepositoryTestObjectMother extends AbstractIntegrationTest {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("Should return top 3 posts")
  void shouldReturnTop3Posts() {
    final var user = userRepository.save(createUser("login"));
    for (int i = 0; i < 10; i++) {
      final var post = postRepository.save(
          createPost("name" + i, "content", i, user)
      );
      commentRepository.saveAll(
          List.of(
              createComment(user, post),
              createComment(user, post)
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
