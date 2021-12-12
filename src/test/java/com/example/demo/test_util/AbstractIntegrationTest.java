package com.example.demo.test_util;

import java.util.Map;
import java.util.stream.Stream;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(
    initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
  private static final String IMAGE_VERSION = "postgres:11.1";

  static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(IMAGE_VERSION);

    private static void startContainers() {
      Startables.deepStart(Stream.of(postgres)).join();
      // we can add further containers
      // here like rabbitmq or other databases
    }

    private static Map<String, String> createConnectionConfiguration() {
      return Map.of(
          "spring.datasource.url", postgres.getJdbcUrl(),
          "spring.datasource.username", postgres.getUsername(),
          "spring.datasource.password", postgres.getPassword()
      );
    }


    @Override
    public void initialize(
        ConfigurableApplicationContext applicationContext) {

      startContainers();

      ConfigurableEnvironment environment =
          applicationContext.getEnvironment();

      MapPropertySource testcontainers = new MapPropertySource(
          "testcontainers",
          (Map) createConnectionConfiguration()
      );

      environment.getPropertySources().addFirst(testcontainers);
    }
  }
}
