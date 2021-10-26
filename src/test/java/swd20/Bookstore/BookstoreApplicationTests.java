package swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;  // JUnit4 -> JUnit 5/Jupiter
import org.junit.jupiter.api.extension.ExtendWith; // JUnit4 -> JUnit 5/Jupiter
import org.springframework.test.context.junit.jupiter.SpringExtension;

import swd20.Bookstore.web.BookController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookstoreApplicationTests {
	
	@Autowired
	private BookController bookController;

	@Test
	void contextLoads() throws Exception {
		assertThat(bookController).isNotNull();
	}

}
