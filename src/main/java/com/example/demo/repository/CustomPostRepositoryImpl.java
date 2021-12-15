package com.example.demo.repository;

import static java.util.Collections.emptyList;

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
  public List<PostView> findTopPosts(int maxPostsCount) {
    final var postsWithComments = em.createQuery(
            """
                   SELECT p.id AS postId, p.name AS postName, p.rating AS rating, a.login AS login
                    FROM Post p
                    INNER JOIN p.author as a
                    ORDER BY rating DESC
                """,
            Tuple.class
        ).setMaxResults(maxPostsCount)
        .getResultList();

    final var res = new LinkedHashMap<Long, PostView>();

    for (Tuple row : postsWithComments) {
      final var postId = row.get("postId", Long.class);
      res.put(postId, new PostView(
          postId,
          row.get("postName", String.class),
          row.get("rating", Double.class),
          row.get("login", String.class),
          new ArrayList<>()
      ));
    }

    final List<Tuple> comments =
        res.isEmpty()
            ? emptyList()
            : em.createQuery(
                    """
                        SELECT c.id AS commentId, c.text AS text, c.createdAt AS createdAt, c.post.id AS postId
                        FROM Comment c
                        WHERE c.post.id in (:postsIds)
                        ORDER BY createdAt DESC
                        """,
                    Tuple.class
                ).setParameter("postsIds", res.keySet())
                .getResultList();

    for (Tuple row : comments) {
      final var postId = row.get("postId", Long.class);
      res.get(postId)
          .comments()
          .add(new CommentView(
              row.get("commentId", Long.class),
              row.get("text", String.class),
              row.get("createdAt", OffsetDateTime.class)
          ));
    }

    return new ArrayList<>(res.values());
  }
}
