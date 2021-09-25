package swd20.Bookstore;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookstoreRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log= LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	
	}
@Bean
	public CommandLineRunner demo(BookstoreRepository bookstoreRepository) {
		return(args) -> {
			log.info("Tallennetaan kirjoja");
		Book b1 = new Book("Aapinen", "Keijo Kirjailija", 1990, "123-456", 20.40);
		Book b2= new Book("Puppe menee kouluun", "Maija Mehiläinen", 2000, "5424-754", 15.30);
			bookstoreRepository.save(b1);
			bookstoreRepository.save(b2);
		};
	}

}
