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
import swd20.Bookstore.domain.User;
import swd20.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log= LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	
	}
	@Bean
	public CommandLineRunner demo(BookstoreRepository bookstoreRepository, CategoryRepository categoryrepository, UserRepository userRepository) {
		return(args) -> {
			log.info("Tallennetaan uusia kategorioita");
			Category c1= new Category("Scifi");
			Category c2= new Category("Dekkari");
			Category c3= new Category("Historia");
			
			categoryrepository.save(c1);
			categoryrepository.save(c2);
			categoryrepository.save(c3);
			
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
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

