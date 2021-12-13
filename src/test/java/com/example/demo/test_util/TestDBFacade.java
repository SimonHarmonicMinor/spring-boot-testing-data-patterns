package com.example.demo.test_util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

public class TestDBFacade {

  @Autowired
  private TestEntityManager testEntityManager;
  @Autowired
  private TransactionTemplate transactionTemplate;

  public <T> Builder<T> persistedOnce(Builder<T> builder) {
    return new Builder<T>() {
      private T entity;

      @Override
      public T build() {
        if (entity == null) {
          entity = persisted(builder).build();
        }
        return entity;
      }
    };
  }

  public <T> Builder<T> persisted(Builder<T> builder) {
    return () -> transactionTemplate.execute(status -> {
      final var entity = builder.build();
      testEntityManager.persistAndFlush(entity);
      return entity;
    });
  }

  public <T> T save(T entity) {
    return transactionTemplate.execute(status -> {
      testEntityManager.persistAndFlush(entity);
      return entity;
    });
  }

  @TestConfiguration
  public static class Config {

    @Bean
    public TestDBFacade testDBFacade() {
      return new TestDBFacade();
    }
  }
}
