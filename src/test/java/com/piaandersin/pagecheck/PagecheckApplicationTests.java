package com.piaandersin.pagecheck;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PagecheckApplicationTests {

	@Test
	void contextLoads() {
            System.out.println(System.getenv("JAVA_HOME"));
            assertTrue(true);
        }

}
