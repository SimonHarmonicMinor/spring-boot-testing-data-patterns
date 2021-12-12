package com.example.demo.repository;

import com.example.demo.repository.query.CommentView;
import com.example.demo.repository.query.PostView;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
class CustomPostRepositoryImpl implements CustomPostRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<PostView> findTopPostsWithLatestComments(int maxPostsCount) {
    final var rows = em.createQuery(
            """
                   SELECT p.id AS postId, p.name AS postName, p.rating AS rating, a.login AS login,
                    c.id AS commentId, c.text AS text, c.createdAt AS createdAt
                    FROM Post p
                    INNER JOIN p.author as a
                    LEFT JOIN p.comments as c
                    ORDER BY rating, createdAt
                """,
            Tuple.class
        ).setMaxResults(maxPostsCount)
        .getResultList();
    final var res = new LinkedHashMap<Long, PostView>();
    for (Tuple row : rows) {
      final var postId = row.get("postId", Long.class);
      final var post = res.computeIfAbsent(postId, k -> new PostView(
          postId,
          row.get("postName", String.class),
          row.get("rating", Double.class),
          row.get("login", String.class),
          new ArrayList<>()
      ));
      final var commentId = row.get("commentId", Long.class);
      if (commentId != null) {
        post.comments().add(new CommentView(
            commentId,
            row.get("text", String.class),
            row.get("createdAt", OffsetDateTime.class)
        ));
      }
    }
    return new ArrayList<>(res.values());
  }
}
