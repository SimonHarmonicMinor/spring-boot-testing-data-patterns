package com.example.demo.repository;

import static com.example.demo.model.CommentTestBuilder.aComment;
import static com.example.demo.model.PostTestBuilder.aPost;
import static com.example.demo.model.UserTestBuilder.aUser;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.repository.query.PostView;
import com.example.demo.test_util.AbstractIntegrationTest;
import com.example.demo.test_util.DBTest;
import com.example.demo.test_util.TestDBFacade;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DBTest
class PostRepositoryTestDataBuilder extends AbstractIntegrationTest {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private TestDBFacade db;

  @Test
  @DisplayName("Should return top 3 posts")
  void shouldReturnTop3Posts() {
    final var user = db.persistedOnce(aUser().withLogin("login"));
    final var comment = aComment().withAuthor(user);
    for (int i = 0; i < 10; i++) {
      db.save(
          aPost()
              .withRating(i)
              .withAuthor(user)
              .withComments(List.of(
                  comment.withText("comment1"),
                  comment.withText("comment2")
              ))
              .build()
      );
    }

    final var res = postRepository.findTopPosts(3);

    assertEquals(3, res.size(), "Unexpected posts count");
    assertEquals(
        res,
        res.stream()
            .sorted(comparing(PostView::rating, reverseOrder()))
            .collect(Collectors.toList()),
        "Posts should be sorted in by rating in descending order"
    );
    assertTrue(
        res.stream()
            .allMatch(post -> post.comments().size() == 2),
        "Each post should have 2 comments"
    );
  }
}
