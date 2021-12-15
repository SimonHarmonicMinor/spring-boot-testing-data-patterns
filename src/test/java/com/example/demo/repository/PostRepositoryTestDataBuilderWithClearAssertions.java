package com.example.demo.repository;

import static com.example.demo.model.CommentTestBuilder.aComment;
import static com.example.demo.model.PostTestBuilder.aPost;
import static com.example.demo.model.UserTestBuilder.aUser;
import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.example.demo.repository.query.PostView;
import com.example.demo.test_util.AbstractIntegrationTest;
import com.example.demo.test_util.DBTest;
import com.example.demo.test_util.TestDBFacade;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DBTest
class PostRepositoryTestDataBuilderWithClearAssertions extends AbstractIntegrationTest {
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

    assertThat(res, hasSize(3));
    assertThat(res, is(sortedByRatingDesc()));
    assertThat(res, hasComments(2));
  }

  private static Matcher<List<PostView>> sortedByRatingDesc() {
    return new TypeSafeMatcher<>() {
      @Override
      protected boolean matchesSafely(List<PostView> item) {
        return item.equals(
            item.stream()
                .sorted(comparing(PostView::rating, reverseOrder()))
                .collect(toList())
        );
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("sorted posts by rating in desc order");
      }

      @Override
      protected void describeMismatchSafely(List<PostView> item, Description mismatchDescription) {
        mismatchDescription.appendText(
            item.stream()
                .map(PostView::rating)
                .collect(toList())
                .toString()
        );
      }
    };
  }

  private static Matcher<List<PostView>> hasComments(int count) {
    return new TypeSafeMatcher<>() {
      @Override
      protected boolean matchesSafely(List<PostView> item) {
        return item.stream()
            .allMatch(post -> post.comments().size() == count);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(count + " comments in each post");
      }

      @Override
      protected void describeMismatchSafely(List<PostView> item, Description mismatchDescription) {
        mismatchDescription.appendText(
            item.stream()
                .map(postView -> format(
                    "PostView[%d] with %d comments",
                    postView.id(),
                    postView.comments().size()
                ))
                .collect(joining(" ; "))
        );
      }
    };
  }
}
