package com.example.demo.test_util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@AutoConfigureTestEntityManager
@ContextConfiguration(classes = TestDBFacade.Config.class)
public @interface DBTest {
}
