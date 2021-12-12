package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private PostRepository postRepository;

  @Test
  void test() {
    assertEquals(0, postRepository.count());
  }
}