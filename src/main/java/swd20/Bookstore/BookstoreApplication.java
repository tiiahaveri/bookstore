package swd20.Bookstore;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookstoreRepository;
import swd20.Bookstore.domain.Category;
import swd20.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log= LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	
	}
	@Bean
	public CommandLineRunner demo(BookstoreRepository bookstoreRepository, CategoryRepository categoryrepository) {
		return(args) -> {
			log.info("Tallennetaan uusia kategorioita");
			Category c1= new Category("Scifi");
			Category c2= new Category("Dekkari");
			Category c3= new Category("Historia");
			
			categoryrepository.save(c1);
			categoryrepository.save(c2);
			categoryrepository.save(c3);
			
			log.info("Tallennetaan kirjoja");
			Book b1 = new Book("Aapinen", "Keijo Kirjailija", 1990, "123-456", 20.40, c2);
			Book b2= new Book("Puppe menee kouluun", "Maija Mehiläinen", 2000, "5424-754", 15.30, c3);
			bookstoreRepository.save(b1);
			bookstoreRepository.save(b2);
			
			log.info("Haetaan kaikki kirjat");
			for(Book book:bookstoreRepository.findAll()) {
				log.info(book.toString());
			}

			
			};
			
				
				
				
				
			}
			
		
	}

