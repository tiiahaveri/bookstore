package swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import swd20.Bookstore.domain.Category;
import swd20.Bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTests {
		
		
		@Autowired
		private CategoryRepository crepository;
		

		
		
	
		@Test
		public void createNewCategory() {
			Category category= new Category("Nuoret");
		
			crepository.save(category);
			assertThat(category.getCategoryid()).isNotNull();
			
		}

	}



