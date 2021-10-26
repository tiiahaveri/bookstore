package swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.Category;

import swd20.Bookstore.domain.BookstoreRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookstoreRepositoryTests {
	
	@Autowired
	private BookstoreRepository brepository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books=brepository.findByTitle("Aapinen");
		assertThat(books.get(0).getAuthor()).isEqualTo("Keijo Kirjailija");
		
	}
	@Test
	public void createNewBook() {
		Category category= new Category("Nuoret");
		Book book= new Book("Neiti Etsivä","Karolyn Keene",1989,"fjjrjer-8948", 20.0, category);
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
		assertThat(book.getCategory().equals(category));
	}

}
